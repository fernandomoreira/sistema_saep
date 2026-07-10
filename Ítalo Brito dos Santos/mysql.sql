
CREATE DATABASE IF NOT EXISTS almoxarifado_senai;
USE almoxarifado_senai;


CREATE TABLE Categoria (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(100) NOT NULL
);


CREATE TABLE Produto (
 id_produto INT AUTO_INCREMENT PRIMARY KEY,
 nome_produto VARCHAR(150) NOT NULL,
 unidade_medida VARCHAR(30) NOT NULL,
 quantidade_estoque INT NOT NULL DEFAULT 0,
 valor_unitario DECIMAL(10, 2) NOT NULL,
 id_categoria INT,
 FOREIGN KEY (id_categoria) REFERENCES Categoria(id) ON DELETE SET NULL
);


CREATE TABLE Movimentacao (
 id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
 tipo ENUM('Entrada', 'Saída') NOT NULL,
 quantidade INT NOT NULL,
 data_movimento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 id_produto INT,
 FOREIGN KEY (id_produto) REFERENCES Produto(id_produto) ON DELETE CASCADE
);

CREATE VIEW vw_estoque AS
SELECT 
 id_produto,
 nome_produto,
 quantidade_estoque,
 valor_unitario,
 (quantidade_estoque * valor_unitario) AS valor_total
FROM Produto;


INSERT INTO Categoria (nome) VALUES 
('Materiais de Limpeza Pesada'),
('Desinfetantes e Álcool'),
('Utensílios de Limpeza');


INSERT INTO Produto (nome_produto, unidade_medida, quantidade_estoque, valor_unitario, id_categoria) VALUES 
('Detergente Industrial 5L', 'Galão', 15, 25.50, 1),
('Álcool em Gel 70% 1L', 'Frasco', 50, 12.00, 2),
('Vassoura de Nylon', 'Unidade', 22, 18.90, 3);


INSERT INTO Movimentacao (tipo, quantidade, id_produto) VALUES 
('Entrada', 10, 1),
('Saída', 5, 2),
('Entrada', 12, 3);



