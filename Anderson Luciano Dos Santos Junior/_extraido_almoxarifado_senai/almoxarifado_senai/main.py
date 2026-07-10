from conexao import conectar


def listar_produtos():
    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
        SELECT 
            p.id_produto,
            p.nome,
            p.unidade_medida,
            p.quantidade,
            p.valor_unitario,
            c.nome
        FROM produto p
        LEFT JOIN categoria c 
        ON p.id_categoria = c.id_categoria
    """)

    produtos = cursor.fetchall()

    print("\n--- PRODUTOS CADASTRADOS ---")

    if len(produtos) == 0:
        print("Nenhum produto cadastrado.")
    else:
        for p in produtos:
            print(f"ID: {p[0]} | Nome: {p[1]} | Unidade: {p[2]} | Qtd: {p[3]} | Valor: R$ {p[4]} | Categoria: {p[5]}")

    cursor.close()
    conexao.close()


def cadastrar_produto():
    conexao = conectar()
    cursor = conexao.cursor()

    nome = input("Nome do produto: ")
    unidade = input("Unidade de medida: ")
    quantidade = int(input("Quantidade: "))
    valor = float(input("Valor unitário: "))
    id_categoria = int(input("ID da categoria: "))

    if quantidade < 0:
        print("Quantidade não pode ser negativa.")
        return

    if valor <= 0:
        print("Valor unitário deve ser maior que zero.")
        return

    cursor.execute("""
        INSERT INTO produto (nome, unidade_medida, quantidade, valor_unitario, id_categoria)
        VALUES (%s, %s, %s, %s, %s)
    """, (nome, unidade, quantidade, valor, id_categoria))

    conexao.commit()
    print("Produto cadastrado com sucesso!")

    cursor.close()
    conexao.close()


def registrar_entrada():
    conexao = conectar()
    cursor = conexao.cursor()

    id_produto = int(input("ID do produto: "))
    quantidade = int(input("Quantidade de entrada: "))
    data = input("Data da entrada AAAA-MM-DD: ")

    cursor.execute("""
        INSERT INTO entrada (id_produto, quantidade, data_entrada)
        VALUES (%s, %s, %s)
    """, (id_produto, quantidade, data))

    cursor.execute("""
        UPDATE produto
        SET quantidade = quantidade + %s
        WHERE id_produto = %s
    """, (quantidade, id_produto))

    conexao.commit()
    print("Entrada registrada com sucesso!")

    cursor.close()
    conexao.close()


def registrar_saida():
    conexao = conectar()
    cursor = conexao.cursor()

    id_produto = int(input("ID do produto: "))
    quantidade = int(input("Quantidade de saída: "))
    data = input("Data da saída AAAA-MM-DD: ")

    cursor.execute("SELECT quantidade FROM produto WHERE id_produto = %s", (id_produto,))
    resultado = cursor.fetchone()

    if resultado is None:
        print("Produto não encontrado.")
        return

    estoque_atual = resultado[0]

    if quantidade > estoque_atual:
        print("Saída não permitida. Estoque insuficiente.")
        return

    cursor.execute("""
        INSERT INTO saida (id_produto, quantidade, data_saida)
        VALUES (%s, %s, %s)
    """, (id_produto, quantidade, data))

    cursor.execute("""
        UPDATE produto
        SET quantidade = quantidade - %s
        WHERE id_produto = %s
    """, (quantidade, id_produto))

    conexao.commit()
    print("Saída registrada com sucesso!")

    cursor.close()
    conexao.close()


def listar_saidas():
    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
        SELECT s.id_saida, p.nome, s.quantidade, s.data_saida
        FROM saida s
        INNER JOIN produto p ON s.id_produto = p.id_produto
        ORDER BY s.data_saida DESC
    """)

    saidas = cursor.fetchall()

    print("\n--- SAÍDAS DE PRODUTOS ---")
    for s in saidas:
        print(f"ID: {s[0]} | Produto: {s[1]} | Quantidade: {s[2]} | Data: {s[3]}")

    cursor.close()
    conexao.close()


def valor_total_categoria():
    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
        SELECT c.nome, SUM(p.quantidade * p.valor_unitario) AS valor_total
        FROM categoria c
        INNER JOIN produto p ON c.id_categoria = p.id_categoria
        GROUP BY c.nome
    """)

    dados = cursor.fetchall()

    print("\n--- VALOR TOTAL POR CATEGORIA ---")
    for d in dados:
        print(f"Categoria: {d[0]} | Valor total: R$ {d[1]}")

    cursor.close()
    conexao.close()


def movimentacoes_periodo():
    conexao = conectar()
    cursor = conexao.cursor()

    data_inicial = input("Data inicial AAAA-MM-DD: ")
    data_final = input("Data final AAAA-MM-DD: ")

    cursor.execute("""
        SELECT 
            p.nome,
            p.unidade_medida,
            COALESCE(SUM(e.quantidade), 0) AS total_entradas,
            COALESCE((
                SELECT SUM(s.quantidade)
                FROM saida s
                WHERE s.id_produto = p.id_produto
                AND s.data_saida BETWEEN %s AND %s
            ), 0) AS total_saidas,
            COALESCE(SUM(e.quantidade), 0) -
            COALESCE((
                SELECT SUM(s.quantidade)
                FROM saida s
                WHERE s.id_produto = p.id_produto
                AND s.data_saida BETWEEN %s AND %s
            ), 0) AS saldo_periodo,
            COALESCE(SUM(e.quantidade * p.valor_unitario), 0) AS valor_entradas,
            COALESCE((
                SELECT SUM(s.quantidade * p.valor_unitario)
                FROM saida s
                WHERE s.id_produto = p.id_produto
                AND s.data_saida BETWEEN %s AND %s
            ), 0) AS valor_saidas
        FROM produto p
        LEFT JOIN entrada e ON p.id_produto = e.id_produto
        AND e.data_entrada BETWEEN %s AND %s
        GROUP BY p.id_produto, p.nome, p.unidade_medida, p.valor_unitario
    """, (
        data_inicial, data_final,
        data_inicial, data_final,
        data_inicial, data_final,
        data_inicial, data_final
    ))

    dados = cursor.fetchall()

    print("\n--- MOVIMENTAÇÕES POR PERÍODO ---")
    for d in dados:
        print(f"Produto: {d[0]} | Unidade: {d[1]} | Entradas: {d[2]} | Saídas: {d[3]} | Saldo: {d[4]} | Valor Entradas: R$ {d[5]} | Valor Saídas: R$ {d[6]}")

    cursor.close()
    conexao.close()


def maior_volume_saida():
    conexao = conectar()
    cursor = conexao.cursor()

    data_inicial = input("Data inicial AAAA-MM-DD: ")
    data_final = input("Data final AAAA-MM-DD: ")

    cursor.execute("""
        SELECT 
            p.nome,
            SUM(s.quantidade) AS total_saida,
            SUM(s.quantidade * p.valor_unitario) AS valor_total_saida
        FROM saida s
        INNER JOIN produto p ON s.id_produto = p.id_produto
        WHERE s.data_saida BETWEEN %s AND %s
        GROUP BY p.nome
        ORDER BY total_saida DESC
    """, (data_inicial, data_final))

    dados = cursor.fetchall()

    print("\n--- PRODUTOS COM MAIOR VOLUME DE SAÍDA ---")
    for d in dados:
        print(f"Produto: {d[0]} | Quantidade total de saída: {d[1]} | Valor financeiro: R$ {d[2]}")

    cursor.close()
    conexao.close()


def estoque_minimo_maximo():
    conexao = conectar()
    cursor = conexao.cursor()

    cursor.execute("""
        SELECT nome, quantidade, valor_unitario
        FROM produto
        WHERE quantidade = 0 OR quantidade >= 100
    """)

    produtos = cursor.fetchall()

    print("\n--- ESTOQUE MÍNIMO OU MÁXIMO ---")
    for p in produtos:
        percentual = (p[1] / 100) * 100
        print(f"Produto: {p[0]} | Quantidade: {p[1]} | Percentual atingido: {percentual:.2f}%")

    cursor.close()
    conexao.close()


def menu():
    while True:
        print("""
===== SISTEMA DE ALMOXARIFADO =====

1 - Listar produtos
2 - Cadastrar produto
3 - Registrar entrada
4 - Registrar saída
5 - Listar saídas por data
6 - Valor total por categoria
7 - Movimentações por período
8 - Produtos com maior volume de saída
9 - Estoque mínimo ou máximo
0 - Sair
""")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            listar_produtos()
        elif opcao == "2":
            cadastrar_produto()
        elif opcao == "3":
            registrar_entrada()
        elif opcao == "4":
            registrar_saida()
        elif opcao == "5":
            listar_saidas()
        elif opcao == "6":
            valor_total_categoria()
        elif opcao == "7":
            movimentacoes_periodo()
        elif opcao == "8":
            maior_volume_saida()
        elif opcao == "9":
            estoque_minimo_maximo()
        elif opcao == "0":
            print("Sistema encerrado.")
            break
        else:
            print("Opção inválida!")


menu()