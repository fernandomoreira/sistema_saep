DELIMITER $$

CREATE TRIGGER trg_atualiza_estoque_entrada
AFTER INSERT ON entrada
FOR EACH ROW
BEGIN
    UPDATE produto 
    SET quantidade = quantidade + NEW.quantidade
    WHERE id = NEW.fk_produto_id;
END$$

CREATE TRIGGER trg_atualiza_estoque_saida
BEFORE INSERT ON saida
FOR EACH ROW
BEGIN
    DECLARE estoque_atual INT;
    
    SELECT quantidade INTO estoque_atual 
    FROM produto 
    WHERE id = NEW.fk_produto_id;
    
    IF estoque_atual < NEW.quantidade THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Erro: quantidade insuficiente no almoxarifado para realizar essa saída!';
    ELSE
        UPDATE produto 
        SET quantidade = quantidade - NEW.quantidade
        WHERE id = NEW.fk_produto_id;
    END IF;
END$$

DELIMITER ;