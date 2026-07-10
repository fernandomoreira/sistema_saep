USE almoxarifado;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    valor_unitario DECIMAL(10,2) NOT NULL
);
SELECT*FROM produtos;

CREATE TABLE entradas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    data_entrada DATE NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
SELECT*FROM entradas;

CREATE TABLE saidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    data_saida DATE NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
SELECT*FROM saidas;

CREATE VIEW vw_estoque AS
SELECT 
    id, nome, categoria, quantidade, valor_unitario,
    (quantidade * valor_unitario) AS valor_total
FROM produtos;
SELECT*FROM vw_estoque;

INSERT INTO produtos (nome, categoria, quantidade, valor_unitario) VALUES 
('Detergente Neutro 5L', 'Limpeza', 45, 12.50),
('Desinfetante Lavanda', 'Limpeza', 0, 4.20),
('Sabão em Pó 10kg', 'Lavandaria', 120, 85.00);

INSERT INTO entradas (produto_id, data_entrada, quantidade) VALUES 
(1, '2026-06-01', 50), (2, '2026-06-02', 10), (3, '2026-06-03', 120);

INSERT INTO saidas (produto_id, data_saida, quantidade) VALUES 
(1, '2026-06-10', 5), (2, '2026-06-15', 10), (3, '2026-06-20', 0);