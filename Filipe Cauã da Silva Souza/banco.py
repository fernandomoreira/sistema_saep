import mysql.connector

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",  # coloque sua senha aqui
        database="estoque"
    )

while True:
    try:
        conexao = conectar()

        if conexao.is_connected():
            print("Conexão com o banco realizada com sucesso!")
            conexao.close()
            break

    except mysql.connector.Error as erro:
        print("Erro ao conectar ao banco:")
        print(erro)

        opcao = input("Deseja tentar novamente? (S/N): ").strip().upper()

        if opcao == "N":
            print("Programa encerrado.")
            break