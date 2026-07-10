CREATE VIEW vw_estoque AS
SELECT 
    p.id AS produto_id,
    p.nome AS nome_produto,
    c.nome AS nome_categoria,
    p.quantidade AS quantidade_atual,
    p.precoUnitario AS preco_unitario,
    (p.quantidade * p.precoUnitario) AS valor_total_item
FROM 
    produto p
INNER JOIN 
    categoria c ON p.fk_categoria_id = c.id;