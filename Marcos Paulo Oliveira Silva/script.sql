CREATE DATABASE Estoque;

USE estoque;

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(30) NOT NULL,
    quantidade_atual INT DEFAULT 0,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    limite_minimo INT DEFAULT 0,
    limite_maximo INT DEFAULT 100,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);

CREATE TABLE Movimentacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT,
    tipo ENUM('ENTRADA', 'SAIDA') NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    data_movimento DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE VIEW vw_estoque AS
SELECT 
    id, 
    nome, 
    quantidade_atual, 
    valor_unitario, 
    (quantidade_atual * valor_unitario) AS valor_total
FROM Produto;


INSERT INTO Categoria (nome) VALUES 
('Detergentes e Sabões'), 
('Desinfetantes'), 
('Equipamentos de Proteção (EPI)');

INSERT INTO Produto (nome, unidade_medida, quantidade_atual, valor_unitario, categoria_id) VALUES 
('Detergente Neutro 5L', 'Galão', 25, 14.90, 1),
('Álcool em Gel 70% 1L', 'Frasco', 60, 11.50, 2),
('Luvas de Nitrilo M', 'Caixa', 8, 42.00, 3);

INSERT INTO Movimentacao (produto_id, tipo, quantidade, valor_unitario) VALUES 
(1, 'ENTRADA', 25, 14.90),
(2, 'ENTRADA', 60, 11.50),
(3, 'ENTRADA', 10, 42.00);

INSERT INTO Movimentacao (produto_id, tipo, quantidade, valor_unitario) VALUES 
(3, 'SAIDA', 2, 42.00);