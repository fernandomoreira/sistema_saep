import mysql.connector
from mysql.connector import Error

def obter_conexao():
    try:
        conn = mysql.connector.connect(
            host="localhost",
            user="root",          
            password="",
            database="almoxarifado" 
        )
        return conn
    except Error as e:
        print(f"Erro ao conectar ao MySQL: {e}")
        return None

def cadastrar_produto(conn, nome, categoria, quantity, valor_unitario):
    if not conn: return
    if not categoria or str(categoria).strip() == "":
        print("Erro de Validação: Categoria é de preenchimento obrigatório.")
        return
    if quantity < 0:
        print("Erro de Validação: Quantidade não pode ser menor que zero.")
        return
    if valor_unitario <= 0:
        print("Erro de Validação: Valor unitário deve ser maior que zero.")
        return
        
    cursor = conn.cursor(buffered=True)
    cursor.execute("INSERT INTO produtos (nome, categoria, quantidade, valor_unitario) VALUES (%s, %s, %s, %s);", 
                   (nome, categoria, quantity, valor_unitario))
    conn.commit()
    cursor.close()
    print(f"Produto '{nome}' cadastrado com sucesso!")

def listar_produtos_da_view(conn):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("SELECT id, nome, categoria, quantidade, valor_unitario, valor_total FROM vw_estoque;")
    print("\n--- LISTAGEM ---")
    for row in cursor.fetchall():
        preco = float(row[4])
        total = float(row[5])
        print(f"ID: {row[0]} | Nome: {row[1]} | Categoria: {row[2]} | Qtd: {row[3]} | Preço Un: R${preco:.2f} | Total: R${total:.2f}")
    cursor.close()


def valor_por_categoria(conn):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("SELECT categoria, SUM(quantidade * valor_unitario) FROM produtos GROUP BY categoria;")
    print("\n--- VALOR TOTAL POR CATEGORIA ---")
    for row in cursor.fetchall():
        valor_total = float(row[1]) if row[1] is not None else 0.0
        print(f"Categoria: {row[0]} | Valor Total: R${valor_total:.2f}")
    cursor.close()

def listar_saidas_decrescente(conn):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("""
        SELECT s.id, p.nome, s.data_saida, s.quantidade 
        FROM saidas s 
        JOIN produtos p ON s.produto_id = p.id 
        ORDER BY s.data_saida DESC;
    """)
    print("\n--- HISTÓRICO DE SAÍDAS ---")
    for row in cursor.fetchall():
        print(f"ID Saída: {row[0]} | Produto: {row[1]} | Data Saída: {row[2]} | Qtd: {row[3]}")
    cursor.close()

def registrar_entrada(conn, produto_id, data_entrada, quantidade):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("INSERT INTO entradas (produto_id, data_entrada, quantidade) VALUES (%s, %s, %s);", (produto_id, data_entrada, quantidade))
    cursor.execute("UPDATE produtos SET quantidade = quantidade + %s WHERE id = %s;", (quantidade, produto_id))
    conn.commit()
    cursor.close()
    print("Entrada registrada e saldo atualizado no estoque!")

def movimentacoes_por_periodo(conn, data_ini, data_fim):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("""
        SELECT 
            p.nome, 
            'Unidade' AS unidade_medida, 
            COALESCE(SUM(e.quantidade), 0) AS total_entradas, 
            COALESCE(SUM(s.quantidade), 0) AS total_saidas,
            (COALESCE(SUM(e.quantidade), 0) - COALESCE(SUM(s.quantidade), 0)) AS saldo_periodo,
            COALESCE(SUM(e.quantidade * p.valor_unitario), 0) AS valor_financeiro_entradas, 
            COALESCE(SUM(s.quantidade * p.valor_unitario), 0) AS valor_financeiro_saidas
        FROM produtos p
        LEFT JOIN entradas e ON p.id = e.produto_id AND e.data_entrada BETWEEN %s AND %s
        LEFT JOIN saidas s ON p.id = s.produto_id AND s.data_saida BETWEEN %s AND %s
        GROUP BY p.id, p.nome, p.valor_unitario;
    """, (data_ini, data_fim, data_ini, data_fim))
    
    print(f"\n--- RELATÓRIO DE MOVIMENTAÇÕES NO PERÍODO ({data_ini} a {data_fim}) ---")
    for r in cursor.fetchall():
        v_ent = float(r[5])
        v_sai = float(r[6])
        print(f"1. Nome: {r[0]} | 2. Unid: {r[1]} | 3. Qtd Entradas: {r[2]} | 4. Qtd Saídas: {r[3]} | 5. Saldo Período: {r[4]} | 6. Val. Fin. Entradas: R${v_ent:.2f} | 7. Val. Fin. Saídas: R${v_sai:.2f}")
    cursor.close()

def maior_volume_saida(conn, data_ini, data_fim):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("""
        SELECT p.nome, SUM(s.quantidade) AS total_qtd, SUM(s.quantidade * p.valor_unitario) AS total_val
        FROM saidas s 
        JOIN produtos p ON s.produto_id = p.id
        WHERE s.data_saida BETWEEN %s AND %s 
        GROUP BY p.id, p.nome 
        ORDER BY total_qtd DESC;
    """, (data_ini, data_fim))
    print("\n--- PRODUTOS COM MAIOR VOLUME DE SAÍDA NO PERÍODO ---")
    for row in cursor.fetchall():
        val_saidas = float(row[2])
        print(f"Produto: {row[0]} | Quantidade total acumulada: {row[1]} | Valor Financeiro total: R${val_saidas:.2f}")
    cursor.close()

def verificar_limites_estoque(conn):
    if not conn: return
    cursor = conn.cursor(buffered=True)
    cursor.execute("SELECT nome, quantidade FROM produtos;")
    print("\n--- STATUS DOS LIMITES DE ESTOQUE ---")
    for nome, qtd in cursor.fetchall():
        if qtd <= 0:
            print(f" ALERTA: {nome} atingiu o limite mínimo permitido! Nível: 0%")
        elif qtd >= 100:
            percentual = (qtd / 100.0) * 100
            print(f" ALERTA: {nome} atingiu/excedeu o limite máximo estipulado! Nível atual: {percentual:.1f}%")
        else:
            percentual = (qtd / 100.0) * 100
            print(f"Estoque Normal: {nome} está em {percentual:.1f}% de ocupação.")
    cursor.close()


conexao = obter_conexao()

if conexao:
    listar_produtos_da_view(conexao)
    
    valor_por_categoria(conexao)
    listar_saidas_decrescente(conexao)
    movimentacoes_por_periodo(conexao, '2026-06-01', '2026-06-30')
    maior_volume_saida(conexao, '2026-06-01', '2026-06-30')
    verificar_limites_estoque(conexao)
    
    conexao.close()