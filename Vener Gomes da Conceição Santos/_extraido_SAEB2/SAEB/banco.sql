DROP DATABASE IF EXISTS estoque;
CREATE DATABASE estoque;
USE estoque;

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    estoque_minimo INT DEFAULT 0,
    estoque_maximo INT DEFAULT 100
);

CREATE TABLE movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    tipo ENUM('ENTRADA','SAIDA') NOT NULL,
    quantidade INT NOT NULL,
    data_mov DATE NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_movimentacao_produto 
        FOREIGN KEY (produto_id) 
        REFERENCES produto(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE OR REPLACE VIEW vw_estoque AS
SELECT
    p.id, 
    p.nome,
    p.categoria, 
    p.unidade_medida,
    p.quantidade,
    p.valor_unitario,
    (p.quantidade * p.valor_unitario) AS valor_total 
FROM produto p;

INSERT INTO produto (nome, categoria, unidade_medida, quantidade, valor_unitario, estoque_minimo, estoque_maximo) VALUES 
('Detergente Neutro', 'Limpeza Geral', 'Frasco 500ml', 10, 2.50, 0, 100),
('Água Sanitária', 'Limpeza Pesada', 'Garrafa 1L', 100, 3.20, 0, 100),
('Esponja de Aço', 'Acessórios', 'Pacote 8un', 45, 4.00, 0, 100);

INSERT INTO movimentacao (produto_id, tipo, quantidade, data_mov, valor_unitario, valor_total) VALUES 
(1, 'ENTRADA', 10, '2026-06-25', 2.50, 25.00),
(2, 'SAIDA', 5, '2026-06-28', 3.20, 16.00),
(3, 'ENTRADA', 45, '2026-06-30', 4.00, 180.00);