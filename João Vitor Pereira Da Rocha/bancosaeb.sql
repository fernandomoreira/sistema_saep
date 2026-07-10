
CREATE DATABASE IF NOT EXISTS saeb_almoxarifado;
USE saeb_almoxarifado;


CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Entrada (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    data_inicial DATETIME NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Saida (
    id_saida INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    data_final DATETIME NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);


CREATE VIEW vw_estoque AS
SELECT 
    id_produto,
    nome,
    quantidade AS quantidade_registrada,
    valor_unitario,
    (quantidade * valor_unitario) AS valor_total_item
FROM Produto;


INSERT INTO Categoria (nome) VALUES ('Detergentes'), ('Desinfetantes'), ('Utensílios');
INSERT INTO Produto (nome, unidade_medida, quantidade, valor_unitario, id_categoria) VALUES 
('Detergente Neutro 500ml', 'Unidade', 50, 2.50, 1),
('Desinfetante Lavanda 5L', 'Galão', 10, 15.00, 2),
('Vassoura de Nylon', 'Unidade', 5, 12.00, 3);
INSERT INTO Entrada (id_produto, quantidade, valor_unitario, data_inicial) VALUES 
(1, 50, 2.50, NOW()), (2, 10, 15.00, NOW()), (3, 5, 12.00, NOW());
INSERT INTO Saida (id_produto, quantidade, data_final) VALUES 
(1, 10, NOW()), (2, 2, NOW()), (1, 5, NOW());