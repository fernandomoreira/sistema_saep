/* Modelo Lógico */

CREATE TABLE Categoria (
    Id_cat INT PRIMARY KEY AUTO_INCREMENT,
    Tipo VARCHAR(30) NOT NULL
);

CREATE TABLE Usuario (
    id_usua INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    Id_produto INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100) NOT NULL,
    Quantidade INT NOT NULL,
    fk_Categoria_Id_cat INT NOT NULL,
    fk_Usuario_id_usua INT NOT NULL,

    CONSTRAINT FK_Produto_Categoria
        FOREIGN KEY (fk_Categoria_Id_cat)
        REFERENCES Categoria(Id_cat)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT FK_Produto_Usuario
        FOREIGN KEY (fk_Usuario_id_usua)
        REFERENCES Usuario(id_usua)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE Movimentacao (
    Id_movimenta INT PRIMARY KEY AUTO_INCREMENT,
    Tipo ENUM('Entrada','Saida') NOT NULL,
    Data DATE NOT NULL,
    Hora TIME NOT NULL,
    Quantidade INT NOT NULL,

    fk_Produto_Id_produto INT NOT NULL,
    fk_Usuario_id_usua INT NOT NULL,

    CONSTRAINT FK_Movimentacao_Produto
        FOREIGN KEY (fk_Produto_Id_produto)
        REFERENCES Produto(Id_produto)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT FK_Movimentacao_Usuario
        FOREIGN KEY (fk_Usuario_id_usua)
        REFERENCES Usuario(id_usua)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);