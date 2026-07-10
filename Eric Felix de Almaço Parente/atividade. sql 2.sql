INSERT INTO categoria (nome) VALUES
('Material de Limpeza'),
('Higiene'),
('Escritório');

INSERT INTO produto (nome, unidade_medida, quantidade, valor_unitario, categoria_id) VALUES
('Detergente', 'UN', 30, 3.50, 1),
('Sabão em pó', 'KG', 20, 12.90, 1),
('Papel higiênico', 'FD', 50, 18.00, 2);

INSERT INTO movimentacao (tipo, quantidade, data_movimentacao, valor_unitario, produto_id) VALUES
('ENTRADA', 30, '2026-06-30', 3.50, 1),
('SAIDA', 5, '2026-06-30', 3.50, 1),
('ENTRADA', 20, '2026-06-30', 12.90, 2);

SELECT * FROM Categoria;
SELECT * FROM Produto;
SELECT * FROM Movimentacao;

USE atividade;
SHOW TABLES;