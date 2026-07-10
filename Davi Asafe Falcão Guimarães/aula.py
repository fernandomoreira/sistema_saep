import sqlite3
from datetime import datetime

# ==========================
# CONEXÃO COM O BANCO
# ==========================
conn = sqlite3.connect("estoque.db")
cursor = conn.cursor()

# ==========================
# CRIAÇÃO DAS TABELAS
# ==========================

cursor.execute("""
CREATE TABLE IF NOT EXISTS produtos(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    categoria TEXT NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_unitario REAL NOT NULL
)
""")

cursor.execute("""
CREATE TABLE IF NOT EXISTS movimentacoes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto_id INTEGER,
    tipo TEXT,
    quantidade INTEGER,
    data TEXT,
    FOREIGN KEY(produto_id) REFERENCES produtos(id)
)
""")

conn.commit()

# ==========================
# CADASTRAR PRODUTO
# ==========================

def cadastrar_produto():

    nome = input("Nome do produto: ").strip()

    if nome == "":
        print("Nome inválido.")
        return

    categoria = input("Categoria: ").strip()

    if categoria == "":
        print("Categoria inválida.")
        return

    try:
        quantidade = int(input("Quantidade: "))

        if quantidade < 0:
            raise ValueError

    except:
        print("Quantidade inválida.")
        return

    try:
        valor = float(input("Valor unitário: "))

        if valor <= 0:
            raise ValueError

    except:
        print("Valor inválido.")
        return

    cursor.execute("""
    INSERT INTO produtos(nome,categoria,quantidade,valor_unitario)
    VALUES(?,?,?,?)
    """,(nome,categoria,quantidade,valor))

    conn.commit()

    print("Produto cadastrado com sucesso!")

# ==========================
# LISTAR PRODUTOS
# ==========================

def listar_produtos():

    cursor.execute("""
    SELECT * FROM produtos
    """)

    produtos = cursor.fetchall()

    print("\nPRODUTOS CADASTRADOS\n")

    for p in produtos:

        print(f"""
ID: {p[0]}
Nome: {p[1]}
Categoria: {p[2]}
Quantidade: {p[3]}
Valor Unitário: R$ {p[4]:.2f}
---------------------------
""")

# ==========================
# ENTRADA
# ==========================

def entrada_produto():

    listar_produtos()

    try:
        id_produto = int(input("ID do produto: "))
        quantidade = int(input("Quantidade de entrada: "))

        if quantidade <=0:
            raise ValueError

    except:
        print("Dados inválidos.")
        return

    cursor.execute("""
    UPDATE produtos
    SET quantidade = quantidade + ?
    WHERE id=?
    """,(quantidade,id_produto))

    cursor.execute("""
    INSERT INTO movimentacoes(produto_id,tipo,quantidade,data)
    VALUES(?,?,?,?)
    """,(id_produto,"ENTRADA",quantidade,
          datetime.now().strftime("%d/%m/%Y %H:%M")))

    conn.commit()

    print("Entrada registrada.")

# ==========================
# SAÍDA
# ==========================

def saida_produto():

    listar_produtos()

    try:

        id_produto = int(input("ID do produto: "))
        quantidade = int(input("Quantidade da saída: "))

    except:

        print("Dados inválidos.")
        return

    cursor.execute("""
    SELECT quantidade
    FROM produtos
    WHERE id=?
    """,(id_produto,))

    resultado = cursor.fetchone()

    if resultado is None:
        print("Produto não encontrado.")
        return

    if quantidade > resultado[0]:
        print("Estoque insuficiente.")
        return

    cursor.execute("""
    UPDATE produtos
    SET quantidade = quantidade-?
    WHERE id=?
    """,(quantidade,id_produto))

    cursor.execute("""
    INSERT INTO movimentacoes(produto_id,tipo,quantidade,data)
    VALUES(?,?,?,?)
    """,(id_produto,"SAIDA",quantidade,
          datetime.now().strftime("%d/%m/%Y %H:%M")))

    conn.commit()

    print("Saída registrada.")

# ==========================
# LISTAR SAÍDAS
# ==========================

def listar_saidas():

    cursor.execute("""

    SELECT
    m.id,
    p.nome,
    m.quantidade,
    m.data

    FROM movimentacoes m

    JOIN produtos p

    ON p.id=m.produto_id

    WHERE tipo='SAIDA'

    ORDER BY datetime(m.data) DESC

    """)

    dados = cursor.fetchall()

    print("\nSAÍDAS\n")

    for d in dados:

        print(d)

# ==========================
# MOVIMENTAÇÕES
# ==========================

def listar_movimentacoes():

    cursor.execute("""

    SELECT

    p.nome,
    m.tipo,
    m.quantidade,
    m.data

    FROM movimentacoes m

    JOIN produtos p

    ON p.id=m.produto_id

    ORDER BY datetime(m.data)

    """)

    dados = cursor.fetchall()

    print("\nMOVIMENTAÇÕES\n")

    for d in dados:

        print(f"""
Produto: {d[0]}
Tipo: {d[1]}
Quantidade: {d[2]}
Data: {d[3]}
------------------------
""")

# ==========================
# VALOR TOTAL POR CATEGORIA
# ==========================

def valor_total_categoria():

    cursor.execute("""

    SELECT

    categoria,
    nome,
    quantidade,
    valor_unitario,
    quantidade*valor_unitario

    FROM produtos

    ORDER BY categoria,nome

    """)

    dados = cursor.fetchall()

    print("\nVALOR TOTAL DO ESTOQUE\n")

    categoria = ""

    for d in dados:

        if categoria != d[0]:

            categoria = d[0]

            print(f"\nCategoria: {categoria}")

        print(f"{d[1]}")
        print(f"Quantidade: {d[2]}")
        print(f"Valor Unitário: R$ {d[3]:.2f}")
        print(f"Total: R$ {d[4]:.2f}")
        print()

# ==========================
# MENU
# ==========================

while True:

    print("""
========= ESTOQUE =========

1 - Cadastrar Produto
2 - Listar Produtos
3 - Registrar Entrada
4 - Registrar Saída
5 - Listar Saídas
6 - Listar Movimentações
7 - Valor Total por Categoria
0 - Sair

===========================
""")

    op = input("Escolha: ")

    if op == "1":
        cadastrar_produto()

    elif op == "2":
        listar_produtos()

    elif op == "3":
        entrada_produto()

    elif op == "4":
        saida_produto()

    elif op == "5":
        listar_saidas()

    elif op == "6":
        listar_movimentacoes()

    elif op == "7":
        valor_total_categoria()

    elif op == "0":
        break

    else:
        print("Opção inválida.")

conn.close()