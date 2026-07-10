import mysql.connector
from datetime import datetime

def conectar():
    
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",  
        database="provasaepbanco"
    )

def listar_dados(titulo, query, params=None, colunas=None):
    print(f"\n--- {titulo} ---")
    try:
        with conectar() as conn, conn.cursor(dictionary=True) as cur:
            cur.execute(query, params or ())
            rows = cur.fetchall()
            if not rows: return print("Nenhum registro encontrado.")
            
            if colunas:
                print(" | ".join([f"{c:<12}" for c in colunas]))
                print("-" * (15 * len(colunas)))
                for r in rows: 
                    valores = list(r.values())
                    print(" | ".join([f"{str(v):<12}" for v in valores[:len(colunas)]]))
            else:
                for r in rows: print(" | ".join([f"{k}: {v}" for k, v in r.items()]))
    except Exception as e: print(f"Erro na consulta: {e}")

def cadastrar_produto():
    print("\n--- Cadastrar Novo Produto ---")
    nome = input("Nome do produto: ").strip()
    try:
        id_cat = int(input("ID da Categoria: "))
        qtd = int(input("Quantidade inicial (>= 0): "))
        val = float(input("Valor unitário (> 0): "))
        lim_min = int(input("Limite mínimo (Padrão 0): ") or 0)
        lim_max = int(input("Limite máximo (Padrão 9999): ") or 9999)
        if qtd < 0 or val <= 0: return print("Erro: Valores inválidos.")
    except ValueError: return print("Erro: Entrada inválida.")

    try:
        with conectar() as conn, conn.cursor() as cur:
            cur.execute("INSERT INTO Produto (nome, id_categoria, quantidade_atual, valor_unitario, limite_minimo, limite_maximo) VALUES (%s, %s, %s, %s, %s, %s)", 
                        (nome, id_cat, qtd, val, lim_min, lim_max))
            conn.commit()
            print("Produto cadastrado com sucesso!")
    except Exception as e: print(f"Erro ao salvar (Verifique se o ID da categoria realmente existe no MySQL): {e}")

def registrar_movimentacao():
    print("\n--- Registrar Movimentação ---")
    try:
        id_prod = int(input("ID do Produto: "))
        tipo = input("Tipo (E/S): ").strip().upper()
        qtd = int(input("Quantidade: "))
        if tipo not in ['E', 'S'] or qtd <= 0: return print("Erro: Tipo ou quantidade inválida.")
        data = input("Data (AAAA-MM-DD HH:MM:SS) ou Enter para agora: ").strip() or datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    except ValueError: return print("Erro: Entrada inválida.")

    try:
        with conectar() as conn, conn.cursor() as cur:
            cur.execute("SELECT quantidade_atual FROM Produto WHERE id_produto = %s", (id_prod,))
            res = cur.fetchone()
            if not res: return print("Erro: Produto não encontrado.")
            
            qtd_atual = res[0]
            if tipo == 'S' and qtd_atual < qtd: return print(f"Erro: Estoque insuficiente ({qtd_atual}).")

            cur.execute("INSERT INTO Movimentacao (id_produto, tipo, quantidade, data_movimentacao) VALUES (%s, %s, %s, %s)", (id_prod, tipo, qtd, data))
            nova_qtd = qtd_atual + qtd if tipo == 'E' else qtd_atual - qtd
            cur.execute("UPDATE Produto SET quantidade_atual = %s WHERE id_produto = %s", (nova_qtd, id_prod))
            conn.commit()
            print(f"Movimentação de {tipo} efetuada!")
    except Exception as e: print(f"Erro na movimentação: {e}")

def menu():
    while True:
        print("\n================ PROVA SAEB - GESTÃO DE ESTOQUE ================")
        print("1. Cadastrar Produto | 2. Movimentação | 3. Estoque Atual (View) | 4. Top Saídas | 5. Alerta Limites | 6. Total por Categoria | 7. Ver Categorias | 0. Sair")
        op = input("Opção: ")
        
        if op == "1": cadastrar_produto()
        
        elif op == "2": registrar_movimentacao()
        
        elif op == "3": 
            listar_dados("Estoque Atual (View)", "SELECT id_produto, nome_produto, nome_categoria, quantidade_atual, valor_unitario, valor_total_item FROM vw_estoque", 
                         colunas=["ID", "Produto", "Categoria", "Qtd", "Val. Unit", "Total Item"])
        elif op == "4": 
            listar_dados("Produtos com Maior Volume de Saída", "SELECT p.nome, SUM(m.quantidade) FROM Movimentacao m JOIN Produto p ON m.id_produto = p.id_produto WHERE m.tipo = 'S' GROUP BY p.id_produto, p.nome ORDER BY 2 DESC")
       
        elif op == "5": 
            listar_dados("Alertas de Limites", "SELECT v.nome_produto, v.quantidade_atual, v.valor_total_item, p.limite_minimo, p.limite_maximo FROM vw_estoque v JOIN Produto p ON v.id_produto = p.id_produto WHERE p.quantidade_atual <= p.limite_minimo OR p.quantidade_atual >= p.limite_maximo")
        
        elif op == "6": 
            listar_dados("Valor Total por Categoria", "SELECT nome_categoria, SUM(valor_total_item) FROM vw_estoque GROUP BY nome_categoria")
        elif op == "7":
            listar_dados("Categorias Cadastradas", "SELECT id_categoria, nome_categoria FROM Categoria ORDER BY id_categoria", colunas=["ID", "Nome Categoria"])
        elif op == "0": break
        else: print("Opção inválida.")

if __name__ == "__main__":
    menu()