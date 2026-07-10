-- Criar banco de dados
CREATE DATABASE EstoqueEmpresa;

USE EstoqueEmpresa;

-- Tabela de Categorias
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL
);

-- Tabela de Produtos
CREATE TABLE produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(150) NOT NULL,
    id_categoria INT NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    valor_unitario DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categorias(id_categoria)
);

-- Tabela de Movimentações
CREATE TABLE movimentacoes (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,

    id_produto INT NOT NULL,

    tipo_movimentacao ENUM('ENTRADA','SAIDA') NOT NULL,

    quantidade INT NOT NULL,

    data_inicial DATETIME NOT NULL,

    data_final DATETIME NOT NULL,

    observacao VARCHAR(255),

    CONSTRAINT fk_produto
        FOREIGN KEY (id_produto)
        REFERENCES produtos(id_produto)
);

-- Inserindo Categorias
INSERT INTO categorias (nome_categoria)
VALUES
('Informática'),
('Escritório'),
('Limpeza');

-- Inserindo Produtos
INSERT INTO produtos
(nome_produto, id_categoria, quantidade_estoque, valor_unitario)
VALUES
('Notebook Dell',1,10,4500.00),
('Mouse USB',1,50,45.90),
('Cadeira Escritório',2,20,650.00),
('Detergente',3,100,3.99);

-- Registrando Entradas
INSERT INTO movimentacoes
(id_produto,tipo_movimentacao,quantidade,data_inicial,data_final,observacao)
VALUES
(1,'ENTRADA',10,'2026-06-01 08:00:00','2026-06-01 08:30:00','Compra fornecedor'),
(2,'ENTRADA',50,'2026-06-01 09:00:00','2026-06-01 09:15:00','Compra fornecedor');

-- Registrando Saídas
INSERT INTO movimentacoes
(id_produto,tipo_movimentacao,quantidade,data_inicial,data_final,observacao)
VALUES
(1,'SAIDA',2,'2026-06-15 14:00:00','2026-06-15 14:10:00','Venda'),
(2,'SAIDA',5,'2026-06-18 10:00:00','2026-06-18 10:05:00','Venda');

-- Consulta completa do estoque
SELECT
    p.id_produto,
    p.nome_produto,
    c.nome_categoria,
    p.quantidade_estoque,
    p.valor_unitario
FROM produtos p
INNER JOIN categorias c
ON p.id_categoria = c.id_categoria;

-- Histórico de movimentações
SELECT
    m.id_movimentacao,
    p.nome_produto,
    m.tipo_movimentacao,
    m.quantidade,
    m.data_inicial,
    m.data_final,
    m.observacao
FROM movimentacoes m
INNER JOIN produtos p
ON m.id_produto = p.id_produto
ORDER BY m.data_inicial DESC;