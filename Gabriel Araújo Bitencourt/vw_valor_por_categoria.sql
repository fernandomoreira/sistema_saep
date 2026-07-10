CREATE VIEW vw_valor_por_categoria AS
SELECT 
    c.id AS categoria_id,
    c.nome AS nome_categoria,
    SUM(p.quantidade) AS total_itens_categoria,
    SUM(p.quantidade * p.precoUnitario) AS valor_total_em_estoque
FROM 
    categoria c
LEFT JOIN 
    produto p ON c.id = p.fk_categoria_id
GROUP BY 
    c.id, c.nome;