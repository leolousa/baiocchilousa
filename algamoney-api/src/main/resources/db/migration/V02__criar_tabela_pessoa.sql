CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(30) NOT NULL,
    bairro VARCHAR(20) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(40) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    ativo boolean
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Leonardo Alves', 'Rua 10', '123', 'Lote 07', 'Setor Leste', '71.909-180', 'Brasília', 'DF', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Carlos Pereira', 'Rua Abacate', '300', 'Lote 09', 'Bairro Amendoeiras', '75.000-300', 'Anápolis', 'GO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Antônio José Pereira Cintra', 'Rua 98', '450', '', 'Setor Graúna I', '74.000-180', 'Goiânia', 'GO', false);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Maria do Carmo Silva', 'Av. Brás Cubas', '120', '', 'Setor Albuquerque II', '89.200-290', 'São Paulo', 'SP', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Juliana Pereira', 'Av Assis Chataubriand', '2', 'Ap 106', 'Setor Bueno', '33.890-008', 'Rio de Janeiro', 'RJ', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Carla Gomes da Silva', 'Rua das Amoras', '99', 'Apto 400-B', 'Bairro Goiá', '75.210-180', 'Pindamonhangaba', 'SP', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Josué Andrade Gomes Silva', 'Rua 23', '003', 'Casa 8', 'Setor Industrial', '74.500-280', 'Viçosa', 'MG', false);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Pedro Pereira Silva', 'Rua dos Pinhais', '120', '', 'Setor das Morangas', '88.190-500', 'Braslândia', 'GO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Gustavo da Fonseca', 'Av Castro Alves', '0', 'Casa 20', 'Setor Oeste', '55.404-300', 'Salvador', 'BA', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Zenaide Gomes de Oliveira', 'Rua 123-B', '22', 'Casa 320', 'Setor Leste', '79.120-130', 'Maringá', 'PR', true);
