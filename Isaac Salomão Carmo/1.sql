DROP DATABASE IF EXISTS Estoque;
CREATE DATABASE Estoque;
USE Estoque;


CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);


CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    id_categoria INT NOT NULL,

    CONSTRAINT fk_produto_categoria
    FOREIGN KEY (id_categoria)
    REFERENCES Categoria(id_categoria)
);


CREATE TABLE Entrada (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    data_inicial DATE NOT NULL,
    quantidade INT NOT NULL,
    id_produto INT NOT NULL,

    CONSTRAINT fk_entrada_produto
    FOREIGN KEY (id_produto)
    REFERENCES Produto(id_produto)
);

CREATE TABLE Saida (
    id_saida INT AUTO_INCREMENT PRIMARY KEY,
    data_final DATE NOT NULL,
    quantidade INT NOT NULL,
    id_produto INT NOT NULL,

    CONSTRAINT fk_saida_produto
    FOREIGN KEY (id_produto)
    REFERENCES Produto(id_produto)
);