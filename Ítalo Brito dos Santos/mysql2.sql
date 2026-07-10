USE almoxarifado_senai;


SELECT p.nome_produto, p.quantidade_estoque, p.valor_unitario, c.nome AS nome_categoria 
FROM Produto p
INNER JOIN Categoria c ON p.id_categoria = c.id;


SELECT m.data_movimento, m.tipo, m.quantidade, p.nome_produto 
FROM Movimentacao m
INNER JOIN Produto p ON m.id_produto = p.id_produto;


SELECT * FROM vw_estoque;