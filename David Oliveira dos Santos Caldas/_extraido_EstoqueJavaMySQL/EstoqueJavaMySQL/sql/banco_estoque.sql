DROP DATABASE IF EXISTS estoque_db;
CREATE DATABASE estoque_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE estoque_db;

CREATE TABLE Usuario (
    Id_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Login VARCHAR(100) NOT NULL UNIQUE,
    Senha VARCHAR(100) NOT NULL
);

CREATE TABLE Categoria (
    Id_Categoria INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Produto (
    Id_Produto INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Unidade_Medida VARCHAR(20) NOT NULL,
    Quantidade INT NOT NULL DEFAULT 0,
    Valor_Unitario DECIMAL(10,2) NOT NULL,
    Estoque_Minimo INT NOT NULL DEFAULT 0,
    Estoque_Maximo INT NOT NULL DEFAULT 100,
    fk_Categoria_Id_Categoria INT NOT NULL,
    CONSTRAINT chk_produto_qtd CHECK (Quantidade >= 0),
    CONSTRAINT chk_produto_valor CHECK (Valor_Unitario > 0),
    CONSTRAINT chk_produto_limites CHECK (Estoque_Minimo = 0 AND Estoque_Maximo = 100),
    FOREIGN KEY (fk_Categoria_Id_Categoria) REFERENCES Categoria(Id_Categoria)
);

CREATE TABLE Movimentacao (
    Id_Movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    Tipo CHAR(1) NOT NULL,
    Quantidade INT NOT NULL,
    Data_Movimentacao DATE NOT NULL,
    fk_Produto_Id_Produto INT NOT NULL,
    fk_Usuario_Id_Usuario INT NOT NULL,
    CONSTRAINT chk_mov_tipo CHECK (Tipo IN ('E', 'S')),
    CONSTRAINT chk_mov_qtd CHECK (Quantidade > 0),
    FOREIGN KEY (fk_Produto_Id_Produto) REFERENCES Produto(Id_Produto),
    FOREIGN KEY (fk_Usuario_Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

INSERT INTO Usuario (Login, Senha) VALUES
('admin', '123'),
('estoquista', '123'),
('gerente', '123');

INSERT INTO Categoria (Nome) VALUES
('Alimentos'),
('Limpeza'),
('Bebidas');

INSERT INTO Produto (Nome, Unidade_Medida, Quantidade, Valor_Unitario, Estoque_Minimo, Estoque_Maximo, fk_Categoria_Id_Categoria) VALUES
('Arroz', 'kg', 50, 25.00, 0, 100, 1),
('Detergente', 'un', 20, 3.50, 0, 100, 2),
('Refrigerante', 'un', 80, 8.00, 0, 100, 3);

INSERT INTO Movimentacao (Tipo, Quantidade, Data_Movimentacao, fk_Produto_Id_Produto, fk_Usuario_Id_Usuario) VALUES
('E', 50, '2026-06-01', 1, 1),
('S', 10, '2026-06-05', 1, 2),
('E', 20, '2026-06-10', 2, 1);

CREATE OR REPLACE VIEW vw_estoque AS
SELECT
    p.Id_Produto,
    p.Nome AS Produto,
    c.Nome AS Categoria,
    p.Unidade_Medida,
    p.Quantidade,
    p.Valor_Unitario,
    (p.Quantidade * p.Valor_Unitario) AS Valor_Total_Item
FROM Produto p
INNER JOIN Categoria c ON c.Id_Categoria = p.fk_Categoria_Id_Categoria;
