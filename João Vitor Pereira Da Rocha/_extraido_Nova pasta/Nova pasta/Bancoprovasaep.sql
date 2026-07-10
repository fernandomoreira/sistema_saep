CREATE database provasaepbanco;

use provasaepbanco;

CREATE TABLE Categoria (
    id_categoria SERIAL PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Produto (
    id_produto SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    id_categoria INTEGER NOT NULL,
    quantidade_atual INTEGER DEFAULT 0 NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    limite_minimo INTEGER DEFAULT 0,
    limite_maximo INTEGER DEFAULT 9999,
    
    CONSTRAINT chk_valor_unitario CHECK (valor_unitario > 0),
    CONSTRAINT chk_quantidade_atual CHECK (quantidade_atual >= 0)
);

CREATE TABLE Movimentacao (
    id_movimentacao SERIAL PRIMARY KEY,
    id_produto INTEGER NOT NULL,
    tipo CHAR(1) NOT NULL, -- 'E' para Entrada, 'S' para Saída
    quantidade INTEGER NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_tipo_movimentacao CHECK (tipo IN ('E', 'S')),
    CONSTRAINT chk_quantidade_mov CHECK (quantidade > 0)
);


ALTER TABLE Produto ADD CONSTRAINT FK_Produto_Categoria
    FOREIGN KEY (id_categoria)
    REFERENCES Categoria (id_categoria)
    ON DELETE RESTRICT;


ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Produto
    FOREIGN KEY (id_produto)
    REFERENCES Produto (id_produto)
    ON DELETE RESTRICT;

