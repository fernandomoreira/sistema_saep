-- 1. Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS estoque_almoxarifado;
USE estoque_almoxarifado;

-- 2. Criação da Tabela Saida (Criada primeiro porque Produto depende dela)
CREATE TABLE IF NOT EXISTS Saida (
    id_Saida INT AUTO_INCREMENT PRIMARY KEY, 
    quantidade INT NOT NULL, 
    data_final DATE NOT NULL
);

-- 3. Criação da Tabela Produto (Possui FK para Saida)
CREATE TABLE IF NOT EXISTS Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(150) NOT NULL, 
    unidade_medida VARCHAR(30) NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL, 
    quantidade_ INT NOT NULL DEFAULT 0, 
    fk_Saida_id_Saida INT,
    FOREIGN KEY (fk_Saida_id_Saida) REFERENCES Saida(id_Saida) ON DELETE SET NULL ON UPDATE CASCADE
);

-- 4. Criação da Tabela Categoria (Possui FK para Produto)
CREATE TABLE IF NOT EXISTS Categoria (
    id_nome INT AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL, 
    fk_Produto_id_produto INT,
    FOREIGN KEY (fk_Produto_id_produto) REFERENCES Produto(id_produto) ON DELETE CASCADE ON UPDATE CASCADE
);
    
-- 5. Criação da Tabela Entrada
CREATE TABLE IF NOT EXISTS Entrada (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY, 
    quantidade INT NOT NULL, 
    valor_utilitario DECIMAL(10, 2) NOT NULL, 
    data_inicial DATE NOT NULL
);
    
-- 6. Criação da Tabela de Associação 'recebe' (N para M entre Produto e Entrada)
CREATE TABLE IF NOT EXISTS recebe (
    fk_Produto_id_produto INT, 
    fk_Entrada_id_entrada INT,
    PRIMARY KEY (fk_Produto_id_produto, fk_Entrada_id_entrada),
    FOREIGN KEY (fk_Produto_id_produto) REFERENCES Produto(id_produto) ON DELETE CASCADE,
    FOREIGN KEY (fk_Entrada_id_entrada) REFERENCES Entrada(id_entrada) ON DELETE CASCADE
);

-- 7. Criação da VIEW obrigatória (vw_estoque) para listar o saldo e valor total
DROP VIEW IF EXISTS vw_estoque;
CREATE VIEW vw_estoque AS 
SELECT 
    id_produto, 
    nome, 
    unidade_medida, 
    quantidade_, 
    valor_unitario, 
    (quantidade_ * valor_unitario) AS valor_total 
FROM Produto;

-- 8. Inserção de Dados Iniciais de Teste (Mínimo de 3 registros exigido pela atividade)
INSERT INTO Saida (quantidade, data_final) VALUES 
(10, '2026-06-10'),
(5, '2026-06-12'),
(0, '2026-06-15');

INSERT INTO Produto (nome, unidade_medida, valor_unitario, quantidade_, fk_Saida_id_Saida) VALUES 
('Detergente Líquido', 'Litro', 1.50, 50, 1),
('Desinfetante Pinho', 'Litro', 4.20, 0, 2),   -- Teste de limite mínimo (0)
('Sacos de Lixo 50L', 'Rolo', 3.00, 100, 3);   -- Teste de limite máximo (100)

INSERT INTO Categoria (nome, fk_Produto_id_produto) VALUES 
('Limpeza', 1),
('Limpeza', 2),
('Descartáveis', 3);

INSERT INTO Entrada (quantidade, valor_utilitario, data_inicial) VALUES 
(50, 1.50, '2026-06-01'),
(20, 4.20, '2026-06-02'),
(100, 3.00, '2026-06-03');

INSERT INTO recebe (fk_Produto_id_produto, fk_Entrada_id_entrada) VALUES 
(1, 1),
(2, 2),
(3, 3);