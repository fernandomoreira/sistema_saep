-- ==========================================
-- CRIAÇÃO DO BANCO
-- ==========================================

CREATE DATABASE Estoque;
USE Estoque;

-- ==========================================
-- TABELA USUARIO
-- ==========================================

CREATE TABLE Usuario (
    Cpf VARCHAR(14) PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL
);

-- ==========================================
-- TABELA CATEGORIA
-- ==========================================

CREATE TABLE Categoria (
    Id_Categoria INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Categoria VARCHAR(100) NOT NULL
);

-- ==========================================
-- TABELA PRODUTO
-- ==========================================

CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Valor DECIMAL(10,2) NOT NULL,
    Quantidade INT NOT NULL,
    fk_Categoria_Id_Categoria INT,
    fk_Usuario_Cpf VARCHAR(14),

    CONSTRAINT FK_Produto_Categoria
    FOREIGN KEY (fk_Categoria_Id_Categoria)
    REFERENCES Categoria(Id_Categoria)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

    CONSTRAINT FK_Produto_Usuario
    FOREIGN KEY (fk_Usuario_Cpf)
    REFERENCES Usuario(Cpf)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

-- ==========================================
-- TABELA MOVIMENTACAO
-- ==========================================

CREATE TABLE Movimentacao (
    id_Movimentacao INT AUTO_INCREMENT PRIMARY KEY,

    Tipo_Entrada_Saida CHAR(1) NOT NULL,

    Data_Hora DATETIME NOT NULL,

    Quantidade INT NOT NULL,

    Valor_Unitario DECIMAL(10,2),

    fk_Produto_id_produto INT,

    fk_Usuario_Cpf VARCHAR(14),

    CONSTRAINT FK_Movimentacao_Produto
    FOREIGN KEY (fk_Produto_id_produto)
    REFERENCES Produto(id_produto)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

    CONSTRAINT FK_Movimentacao_Usuario
    FOREIGN KEY (fk_Usuario_Cpf)
    REFERENCES Usuario(Cpf)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

-- ==========================================
-- TABELA TELEFONE
-- ==========================================

CREATE TABLE Telefone (
    id_Telefone INT AUTO_INCREMENT PRIMARY KEY,

    DDD VARCHAR(3),

    Numero VARCHAR(15),

    fk_Usuario_Cpf VARCHAR(14),

    CONSTRAINT FK_Telefone_Usuario
    FOREIGN KEY (fk_Usuario_Cpf)
    REFERENCES Usuario(Cpf)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);