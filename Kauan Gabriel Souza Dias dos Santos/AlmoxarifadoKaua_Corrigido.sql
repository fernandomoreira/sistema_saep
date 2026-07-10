CREATE DATABASE Almoxarifado;
USE Almoxarifado;

CREATE TABLE Empresa (
 id_empresa INT PRIMARY KEY,
 razao_social VARCHAR(100) NOT NULL,
 nome_fantasia VARCHAR(100),
 cnpj CHAR(14) UNIQUE,
 telefone VARCHAR(15),
 email VARCHAR(100),
 endereco VARCHAR(100)
);

CREATE TABLE Categoria(
 id_categoria INT PRIMARY KEY,
 nome VARCHAR(50) NOT NULL,
 descricao VARCHAR(200),
 id_empresa INT NOT NULL,
 FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

CREATE TABLE Produto(
 id_produto INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(100) NOT NULL,
 descricao VARCHAR(255),
 valor_unitario DECIMAL(10,2),
 unidade_medida VARCHAR(20),
 quantidade INT DEFAULT 0,
 id_categoria INT NOT NULL,
 FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Entrada(
 id_entrada INT AUTO_INCREMENT PRIMARY KEY,
 data_entrada DATE,
 quantidade INT NOT NULL,
 observacao VARCHAR(255),
 id_produto INT NOT NULL,
 FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Saida(
 id_saida INT AUTO_INCREMENT PRIMARY KEY,
 data_saida DATE,
 quantidade INT NOT NULL,
 observacao VARCHAR(255),
 id_produto INT NOT NULL,
 FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);
