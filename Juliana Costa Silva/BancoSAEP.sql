
CREATE DATABASE IF NOT EXISTS provasaep;
USE provasaep;


CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    quantidade INT DEFAULT 0,
    valor_unitario DECIMAL(10, 2) DEFAULT 0.00,
    unidade_medida VARCHAR(50)
);


CREATE TABLE ENTRADA (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    data_inicial DATE NOT NULL,
    quantidade_entrada INT NOT NULL,
    FK_PRODUTO_id_produto INT NOT NULL,
    CONSTRAINT fk_entrada_produto FOREIGN KEY (FK_PRODUTO_id_produto) 
        REFERENCES produto(id_produto) ON DELETE CASCADE
);


CREATE TABLE SAIDA (
    id_saida INT AUTO_INCREMENT PRIMARY KEY,
    data_final DATE NOT NULL,
    quantidade_saida INT NOT NULL,
    FK_PRODUTO_id_produto INT NOT NULL,
    CONSTRAINT fk_saida_produto FOREIGN KEY (FK_PRODUTO_id_produto) 
        REFERENCES produto(id_produto) ON DELETE CASCADE
);


CREATE OR REPLACE VIEW v_relatorio_estoque AS
SELECT 
    p.id_produto AS ID,
    p.nome AS Produto,
    p.categoria AS Categoria,
    p.unidade_medida AS Unidade,
    p.valor_unitario AS 'Preço Unitário',
    p.quantidade AS 'Estoque Atual',
    COALESCE((SELECT SUM(e.quantidade_entrada) FROM ENTRADA e WHERE e.FK_PRODUTO_id_produto = p.id_produto), 0) AS 'Total Entradas',
    COALESCE((SELECT SUM(s.quantidade_saida) FROM SAIDA s WHERE s.FK_PRODUTO_id_produto = p.id_produto), 0) AS 'Total Saídas',
    (p.valor_unitario * p.quantidade) AS 'Valor Total em Estoque'
FROM 
    produto p;