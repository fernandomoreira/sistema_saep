DROP DATABASE IF EXISTS controle_estoque;

CREATE DATABASE controle_estoque;
USE controle_estoque;

-- Tabela de usuários
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela de categorias
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL
);

-- Tabela de produtos
CREATE TABLE produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(65,2) NOT NULL, -- Perfeito! O máximo que o MySQL aceita.
    id_categoria INT NOT NULL,
    id_usuario INT NOT NULL,

    FOREIGN KEY (id_categoria)
        REFERENCES categorias(id_categoria),

    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
);

-- Tabela de movimentações
CREATE TABLE movimentacoes (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimentacao ENUM('ENTRADA','SAIDA') NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_produto INT NOT NULL,
    id_usuario INT NOT NULL,

    FOREIGN KEY (id_produto)
        REFERENCES produtos(id_produto),

    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
);

-- Inserção de usuários de teste
INSERT INTO usuarios(login, senha)
VALUES
('admin', '123'),
('joao', '456');

-- Inserção de categorias do Almoxarifado
INSERT INTO categorias(nome_categoria)
VALUES
('Roupas'),
('Perifericos'),
('Material de Escritorio'),
('Material de Limpeza'),
('EPI (Equipamento de Protecao Individual)'),
('Ferramentas e Ferragens'),
('Suprimentos de Informatica'),
('Manutencao e Reparos');

-- Inserção de produtos (Corrigido: 'quantidade' no singular e id_usuario válido)
INSERT INTO produtos(nome, quantidade, preco, id_categoria, id_usuario) VALUES
('Caneta Azul Bic', 100, 2.50, 3, 1),
('Detergente Neutro', 50, 7.20, 4, 1), 
('Mouse sem fio', 15, 30.55, 7, 2); -- Alterado de 3 para 2 (usuário 'joao')

-- Consulta com INNER JOIN para o relatório do Almoxarifado
SELECT 
    p.nome AS Nome_do_Produto, 
    p.quantidade AS Estoque_Atual, 
    p.preco AS Preco_Unitario,
    c.nome_categoria AS Categoria,
    u.login AS Cadastrado_Por
FROM produtos p
INNER JOIN categorias c ON p.id_categoria = c.id_categoria
INNER JOIN usuarios u ON p.id_usuario = u.id_usuario;