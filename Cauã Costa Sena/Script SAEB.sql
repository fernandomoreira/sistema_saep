USE almoxarifadodb;

-- 2. CRIAR A TABELA: USUARIO
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    usuario_login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. CRIAR A TABELA: CATEGORIA
CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    nome_categoria VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id_categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. CRIAR A TABELA: PRODUTO (Relacionamentos: Cadastra e Classifica)
-- 4. CRIAR A TABELA: PRODUTO (Relacionamentos: Cadastra e Classifica)
CREATE TABLE IF NOT EXISTS produto (
    id_produto INT NOT NULL AUTO_INCREMENT,
    nome_produto VARCHAR(150) NOT NULL,
    unidade_medida VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    preco DECIMAL(10, 2) NOT NULL, -- Corrigido para NOT NULL (sem underline)
    id_categoria INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_produto),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
    CONSTRAINT fk_produto_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. CRIAR A TABELA: MOVIMENTACAO (Relacionamentos: Possui e Realiza)
CREATE TABLE IF NOT EXISTS movimentacao (
    id_movimentacao INT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    data DATE NOT NULL,
    tipo VARCHAR(20) NOT NULL, -- 'Entrada' ou 'Saida'
    id_produto INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_movimentacao),
    CONSTRAINT fk_movimentacao_produto FOREIGN KEY (id_produto) REFERENCES produto (id_produto),
    CONSTRAINT fk_movimentacao_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. CRIAR A VIEW: VW_ESTOQUE
CREATE OR REPLACE VIEW vw_estoque AS
SELECT 
    p.id_produto AS id_produto,
    p.nome_produto AS nome_produto,
    p.quantidade AS quantidade,
    p.preco AS valor_unitario,
    (p.quantidade * p.preco) AS valor_total_item,
    u.usuario_login AS usuario_responsavel
FROM produto p
INNER JOIN usuario u ON p.id_usuario = u.id_usuario;

-- 7. INSERÇÃO DE DADOS INICIAIS
INSERT INTO usuario (usuario_login, senha) VALUES 
('almoxarife_joao', 'senha123'),
('maria_controle', 'admin2026'),
('carlos_suporte', 'secure456')
ON DUPLICATE KEY UPDATE id_usuario=id_usuario;

INSERT INTO categoria (nome_categoria) VALUES 
('Material de Escritorio'),
('Informatica e TI'),
('Produtos de Limpeza')
ON DUPLICATE KEY UPDATE id_categoria=id_categoria;

INSERT INTO produto (nome_produto, unidade_medida, quantidade, preco, id_categoria, id_usuario) VALUES 
('Resma Papel A4', 'Pacote', 45, 32.50, 1, 1),
('Mouse Optico USB', 'Unidade', 12, 45.00, 2, 1),
('Detergente Liquido 500ml', 'Unidade', 105, 2.80, 3, 2)
ON DUPLICATE KEY UPDATE id_produto=id_produto;

INSERT INTO movimentacao (quantidade, data, tipo, id_produto, id_usuario) VALUES 
(10, '2026-06-01', 'Entrada', 1, 1),
(2, '2026-06-15', 'Saida', 2, 2),
(50, '2026-06-20', 'Entrada', 3, 3)
ON DUPLICATE KEY UPDATE id_movimentacao=id_movimentacao;