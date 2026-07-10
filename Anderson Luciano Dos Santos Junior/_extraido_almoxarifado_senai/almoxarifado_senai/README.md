# 📦 Sistema de Almoxarifado

Projeto desenvolvido em **Python + MySQL** para gerenciamento de almoxarifado.

## 📋 Requisitos

Antes de executar o sistema é necessário ter instalado:

- Python 3.x
- MySQL Server
- MySQL Workbench (opcional, mas recomendado)
- Visual Studio Code

---

# 🚀 Instalação

## 1. Clonar ou baixar o projeto

Baixe a pasta do projeto e abra no Visual Studio Code.

---

## 2. Instalar o MySQL

Caso não possua o MySQL instalado:

https://dev.mysql.com/downloads/

Durante a instalação crie o usuário **root** e defina uma senha (ou deixe sem senha, caso seja permitido).

---

## 3. Criar o banco de dados

Abra o **MySQL Workbench**.

Abra o arquivo:

```
script_banco.sql
```

Execute todo o script (ícone do raio ⚡).

Isso irá criar:

- Banco de dados
- Tabelas
- Registros iniciais
- View `vw_estoque`

---

## 4. Instalar o conector do MySQL no Python

Abra o terminal do VS Code e execute:

```bash
pip install mysql-connector-python
```

Caso utilize Linux ou Mac:

```bash
pip3 install mysql-connector-python
```

---

## 5. Configurar a conexão

Abra o arquivo:

```
conexao.py
```

Configure conforme seu MySQL.

Exemplo sem senha:

```python
import mysql.connector

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="almoxarifado"
    )
```

Exemplo com senha:

```python
import mysql.connector

def conectar():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="123456",
        database="almoxarifado"
    )
```

Troque a senha pela senha cadastrada no seu MySQL.

---

# ▶️ Executando o sistema

Abra o terminal na pasta do projeto e execute:

```bash
python main.py
```

ou

```bash
python3 main.py
```

---

# 📁 Estrutura do projeto

```
ALMOXARIFADO_SENAI
│
├── docs
│   └── DER.png
│
├── conexao.py
├── main.py
├── script_banco.sql
└── README.md
```

---

# 📌 Funcionalidades

- Listar produtos
- Cadastrar produtos
- Registrar entrada
- Registrar saída
- Listar saídas por data
- Valor total por categoria
- Movimentações por período
- Produtos com maior volume de saída
- Produtos com estoque mínimo ou máximo

---

# 🛠 Tecnologias utilizadas

- Python
- MySQL
- MySQL Connector
- Visual Studio Code

---

# 👨‍💻 Desenvolvedor

Projeto desenvolvido para atividade acadêmica do SENAI.