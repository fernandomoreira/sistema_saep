create database almoxerife;
use almoxerife;
CREATE TABLE categoria (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE produto (
    id INT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    precoUnitario DECIMAL(10,2) NOT NULL CHECK (precoUnitario >= 0),
    quantidade INT NOT NULL DEFAULT 0 CHECK (quantidade >= 0), -- Estoque atual nunca fica negativo
    fk_categoria_id INT NOT NULL
);

CREATE TABLE entrada (
    id INT PRIMARY KEY,
    data_movimentacao DATE NOT NULL,
    valorUnitario DECIMAL(10,2) NOT NULL CHECK (valorUnitario >= 0),
    quantidade INT NOT NULL CHECK (quantidade > 0), -- Força a entrada a ser maior que zero
    fk_produto_id INT NOT NULL
);

CREATE TABLE saida (
    id INT PRIMARY KEY,
    data_movimentacao DATE NOT NULL,
    valorUnitario DECIMAL(10,2) NOT NULL CHECK (valorUnitario >= 0),
    quantidade INT NOT NULL CHECK (quantidade > 0), -- Força a saída a ser maior que zero
    fk_produto_id INT NOT NULL
);
 
-- Relacionamentos com restrições de segurança (RESTRICT)

ALTER TABLE produto ADD CONSTRAINT FK_produto_categoria
    FOREIGN KEY (fk_categoria_id)
    REFERENCES categoria (id)
    ON DELETE RESTRICT; -- Bloqueia a exclusão da categoria se houver produtos nela
 
ALTER TABLE entrada ADD CONSTRAINT FK_entrada_produto
    FOREIGN KEY (fk_produto_id)
    REFERENCES produto (id)
    ON DELETE RESTRICT; -- Protege o histórico de entradas (auditoria)
 
ALTER TABLE saida ADD CONSTRAINT FK_saida_produto
    FOREIGN KEY (fk_produto_id)
    REFERENCES produto (id)
    ON DELETE RESTRICT; -- Protege o histórico de saídas (auditoria)