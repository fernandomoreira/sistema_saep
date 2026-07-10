import mysql.connector
from datetime import date


# ===========================
# CONEXÃO COM O BANCO
# ===========================

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",   # Coloque sua senha do MySQL aqui, se tiver
        database="estoque"
    )


# ===========================
# MENU
# ===========================

def menu():
    print("\n==============================")
    print("     CONTROLE DE ESTOQUE")
    print("==============================")
    print("1 - Cadastrar Categoria")
    print("2 - Cadastrar Produto")
    print("3 - Registrar Entrada")
    print("4 - Registrar Saída")
    print("5 - Listar Produtos")
    print("6 - Listar Movimentações")
    print("7 - Mostrar Estoque")
    print("0 - Sair")


# ===========================
# CADASTRAR CATEGORIA
# ===========================

def cadastrar_categoria():
    nome = input("Nome da categoria: ")

    conexao = conectar()
    cursor = conexao.cursor()

    sql = "INSERT INTO Categoria(nome) VALUES(%s)"
    cursor.execute(sql, (nome,))

    conexao.commit()

    cursor.close()
    conexao.close()

    print("Categoria cadastrada com sucesso!")


# ===========================
# CADASTRAR PRODUTO
# ===========================

def cadastrar_produto():

    nome = input("Nome do produto: ")
    unidade = input("Unidade de medida: ")
    quantidade = int(input("Quantidade: "))
    valor = float(input("Valor unitário: "))
    categoria = int(input("ID da categoria: "))

    conexao = conectar()
    cursor = conexao.cursor()

    sql = """
    INSERT INTO Produto
    (nome, unidade_medida, quantidade, valor_unitario, id_categoria)
    VALUES (%s,%s,%s,%s,%s)
    """

    cursor.execute(sql, (nome, unidade, quantidade, valor, categoria))

    conexao.commit()

    cursor.close()
    conexao.close()

    print("Produto cadastrado com sucesso!")


# ===========================
# REGISTRAR ENTRADA
# ===========================

def registrar_entrada():

    produto = int(input("ID do produto: "))
    quantidade = int(input("Quantidade: "))

    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
    INSERT INTO Movimentacao
    (id_produto,tipo,quantidade,data_movimentacao)
    VALUES(%s,'ENTRADA',%s,%s)
    """, (produto, quantidade, date.today()))

    cursor.execute("""
    UPDATE Produto
    SET quantidade = quantidade + %s
    WHERE id_produto = %s
    """, (quantidade, produto))

    conexao.commit()

    cursor.close()
    conexao.close()

    print("Entrada registrada com sucesso!")


# ===========================
# REGISTRAR SAÍDA
# ===========================

def registrar_saida():

    produto = int(input("ID do produto: "))
    quantidade = int(input("Quantidade: "))

    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute(
        "SELECT quantidade FROM Produto WHERE id_produto=%s",
        (produto,)
    )

    estoque = cursor.fetchone()

    if estoque is None:
        print("Produto não encontrado!")
        cursor.close()
        conexao.close()
        return

    if quantidade > estoque[0]:
        print("Estoque insuficiente!")
        cursor.close()
        conexao.close()
        return

    cursor.execute("""
    INSERT INTO Movimentacao
    (id_produto,tipo,quantidade,data_movimentacao)
    VALUES(%s,'SAIDA',%s,%s)
    """, (produto, quantidade, date.today()))

    cursor.execute("""
    UPDATE Produto
    SET quantidade = quantidade - %s
    WHERE id_produto = %s
    """, (quantidade, produto))

    conexao.commit()

    cursor.close()
    conexao.close()

    print("Saída registrada com sucesso!")


# ===========================
# LISTAR PRODUTOS
# ===========================

def listar_produtos():

    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
    SELECT
        id_produto,
        nome,
        unidade_medida,
        quantidade,
        valor_unitario,
        id_categoria
    FROM Produto
    """)

    produtos = cursor.fetchall()

    print("\n===== PRODUTOS =====")

    for p in produtos:
        print(p)

    cursor.close()
    conexao.close()


# ===========================
# LISTAR MOVIMENTAÇÕES
# ===========================

def listar_movimentacoes():

    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
    SELECT *
    FROM Movimentacao
    ORDER BY data_movimentacao DESC
    """)

    movimentacoes = cursor.fetchall()

    print("\n===== MOVIMENTAÇÕES =====")

    for m in movimentacoes:
        print(m)

    cursor.close()
    conexao.close()


# ===========================
# MOSTRAR ESTOQUE (VIEW)
# ===========================

def mostrar_estoque():

    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("SELECT * FROM vw_estoque")

    estoque = cursor.fetchall()

    print("\n===== ESTOQUE =====")

    for item in estoque:
        print(item)

    cursor.close()
    conexao.close()


# ===========================
# PROGRAMA PRINCIPAL
# ===========================

while True:

    menu()

    opcao = input("\nEscolha uma opção: ")

    if opcao == "1":
        cadastrar_categoria()

    elif opcao == "2":
        cadastrar_produto()

    elif opcao == "3":
        registrar_entrada()

    elif opcao == "4":
        registrar_saida()

    elif opcao == "5":
        listar_produtos()

    elif opcao == "6":
        listar_movimentacoes()

    elif opcao == "7":
        mostrar_estoque()

    elif opcao == "0":
        print("Programa encerrado.")
        break

    else:
        print("Opção inválida!")