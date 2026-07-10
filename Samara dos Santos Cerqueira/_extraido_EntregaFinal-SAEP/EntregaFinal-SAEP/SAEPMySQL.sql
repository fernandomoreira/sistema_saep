DROP TABLE IF EXISTS saida;
DROP TABLE IF EXISTS entrada;
DROP TABLE IF EXISTS produto;

CREATE TABLE produto (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE entrada (

    id INT NOT NULL AUTO_INCREMENT,

    data_entrada DATE NOT NULL,

    quantidade INT NOT NULL,

    produto_id INT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_entrada_produto
        FOREIGN KEY(produto_id)
        REFERENCES produto(id)

);

CREATE TABLE saida (

    id INT NOT NULL AUTO_INCREMENT,

    data_saida DATE NOT NULL,

    quantidade INT NOT NULL,

    produto_id INT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_saida_produto
        FOREIGN KEY(produto_id)
        REFERENCES produto(id)

);

CREATE OR REPLACE VIEW vw_estoque AS

SELECT

    id,
    nome,
    categoria,
    unidade_medida,
    quantidade,
    valor_unitario,
    (quantidade * valor_unitario) AS valor_total

FROM produto;

SELECT * FROM vw_estoque;