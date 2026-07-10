INSERT INTO categoria (id, nome) VALUES 
(1, 'Papelaria e Escritório'),
(2, 'Informática e TI'),
(3, 'Limpeza e Higiene');

INSERT INTO produto (id, nome, precoUnitario, quantidade, fk_categoria_id) VALUES 
(101, 'Caneta Esferográfica Azul Bic', 1.50, 150, 1), 
(102, 'Mouse Óptico Sem Fio Logitech', 49.90, 20, 2), 
(103, 'Detergente Líquido Neutro 500ml', 2.20, 10, 3); 