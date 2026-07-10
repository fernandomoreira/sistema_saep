# Sistema de Estoque Java + MySQL

Projeto simples em Java com JDBC, Maven e MySQL.

## Importante
O Java não conecta diretamente ao MySQL Workbench. Ele conecta ao **MySQL Server**. O Workbench é a ferramenta visual usada para criar/ver o banco.

## Como rodar no VS Code

1. Instale o JDK 17 ou superior.
2. Instale a extensão **Extension Pack for Java** no VS Code.
3. Instale o MySQL Server e o MySQL Workbench.
4. Abra o MySQL Workbench e execute o arquivo:
   `sql/banco_estoque.sql`
5. Abra este projeto no VS Code.
6. Edite `src/conexao/Conexao.java` e coloque sua senha do MySQL:
   ```java
   private static final String SENHA = "sua_senha";
   ```
7. No VS Code, abra `src/Main.java` e clique em **Run**.

## Banco
Banco usado: `estoque_db`

A view obrigatória é:
`vw_estoque`

Ela calcula o valor total por item de produto:
`Quantidade * Valor_Unitario`

## Funcionalidades
- Listar produtos cadastrados.
- Cadastrar produtos com validação de valor unitário, quantidade e categoria.
- Registrar entradas de estoque.
- Registrar saídas de estoque.
- Atualizar automaticamente o saldo do estoque.
- Listar valor total por categoria.
- Listar saídas por data decrescente.
- Listar movimentações por período.
- Listar produtos com maior volume de saída por período.
- Identificar produtos nos limites mínimo 0 e máximo 100.
