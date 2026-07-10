DROP VIEW IF EXISTS vw_estoque;

CREATE VIEW vw_estoque AS
SELECT
    Produto.id_produto,
    Produto.nome,
    Categoria.nome AS categoria,
    Produto.quantidade,
    Produto.valor_unitario,
    Produto.quantidade * Produto.valor_unitario AS valor_total
FROM Produto
INNER JOIN Categoria
ON Produto.id_categoria = Categoria.id_categoria;

SELECT * FROM vw_estoque;