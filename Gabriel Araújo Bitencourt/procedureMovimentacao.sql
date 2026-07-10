DELIMITER $$

CREATE PROCEDURE sp_relatorio_movimentacoes(
    IN p_data_inicial DATE, 
    IN p_data_final DATE
)
BEGIN
    SELECT 
        'ENTRADA' AS tipo_movimentacao,
        e.id AS movimento_id,
        e.data_movimentacao,
        p.nome AS nome_produto,
        e.quantidade,
        e.valorUnitario,
        (e.quantidade * e.valorUnitario) AS valor_total
    FROM entrada e
    JOIN produto p ON e.fk_produto_id = p.id
    WHERE e.data_movimentacao BETWEEN p_data_inicial AND p_data_final

    UNION ALL

    SELECT 
        'SAÍDA' AS tipo_movimentacao,
        s.id AS movimento_id,
        s.data_movimentacao,
        p.nome AS nome_produto,
        s.quantidade,
        s.valorUnitario,
        (s.quantidade * s.valorUnitario) AS valor_total
    FROM saida s
    JOIN produto p ON s.fk_produto_id = p.id
    WHERE s.data_movimentacao BETWEEN p_data_inicial AND p_data_final
    
    ORDER BY data_movimentacao ASC;
END$$

DELIMITER ;