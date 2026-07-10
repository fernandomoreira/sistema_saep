CREATE VIEW vw_estoque AS
SELECT
    p.id_produto,
    p.nome AS produto,
    c.nome AS categoria,
    p.quantidade,
    p.valor_unitario,
    (p.quantidade * p.valor_unitario) AS valor_total
FROM Produto p
INNER JOIN Categoria c
ON p.id_categoria = c.id_categoria;