-- banco
CREATE DATABASE almoxarifado;
USE almoxarifado;

-- categoria
CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY IDENTITY(1,1),
    nome_categoria VARCHAR(100) NOT NULL
);

-- produto
CREATE TABLE produto (
    id_produto INT PRIMARY KEY IDENTITY(1,1),
    nome_produto VARCHAR(150) NOT NULL,
    id_categoria INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    valor_unitario DECIMAL(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- movimentação do estoque
CREATE TABLE movimento_almoxarifado (
    id_movimento INT PRIMARY KEY,
    id_produto INT NOT NULL,
    data_movimento DATE NOT NULL,
    tipo_movimento VARCHAR(20) NOT NULL CHECK (tipo_movimento IN ('entrada', 'saida')),
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-- guardar relatório 
CREATE TABLE relatorio_almoxarifado (
    id_relatorio INT PRIMARY KEY IDENTITY(1,1),
    data_inicial DATE NOT NULL,
    data_final DATE NOT NULL,
    saldo_inicial INT NOT NULL,
    saldo_final INT NOT NULL,
    id_produto INT NOT NULL,
    nome_produto VARCHAR(150) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    quantidade_produto INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_relatorio_produto
        FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);