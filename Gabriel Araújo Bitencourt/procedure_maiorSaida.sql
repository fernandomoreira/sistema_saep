DELIMITER $$

CREATE PROCEDURE sp_produtos_maior_saida(
    IN p_data_inicial DATE,
    IN p_data_final DATE
)
BEGIN
    SELECT 
        p.id AS produto_id,
        p.nome AS nome_produto,
        SUM(s.quantidade) AS quantidade_total_saida,
        SUM(s.quantidade * s.valorUnitario) AS valor_total_financeiro_saida
    FROM 
        saida s
    INNER JOIN 
        produto p ON s.fk_produto_id = p.id
    WHERE 
        s.data_movimentacao BETWEEN p_data_inicial AND p_data_final
    GROUP BY 
        p.id, p.nome
    ORDER BY 
        quantidade_total_saida DESC; -- Ordena do maior volume de saídas para o menor
END$$

DELIMITER ;