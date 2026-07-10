CREATE DATABASE ESTOQUE_SAEP;

CREATE TABLE Produto (
    Nome VARCHAR(50),
    Categoria VARCHAR(50),
    Quantidade INT,
    Valor_Unitario DECIMAL(10,2),
    id_produto INT PRIMARY KEY,
    Unidade_medida VARCHAR(50)
);

CREATE TABLE Entrada (
    Data_inicial DATE,
    Quantidade_entrada INT,
    id_Entrada INT PRIMARY KEY,
    fk_Produto_id_produto INT
);

CREATE TABLE Saida (
    Data_final DATE,
    Quantidade_saida INT,
    id_saida INT PRIMARY KEY,
    fk_Produto_id_produto INT
);

ALTER TABLE Entrada
ADD CONSTRAINT FK_Entrada_2
FOREIGN KEY (fk_Produto_id_produto)
REFERENCES Produto(id_produto)
ON DELETE CASCADE;

ALTER TABLE Saida
ADD CONSTRAINT FK_Saida_2
FOREIGN KEY (fk_Produto_id_produto)
REFERENCES Produto(id_produto)
ON DELETE CASCADE;