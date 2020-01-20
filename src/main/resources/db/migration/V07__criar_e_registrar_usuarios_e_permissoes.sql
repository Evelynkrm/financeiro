CREATE TABLE TB_USUARIO (
     id BIGINT(20) PRIMARY KEY,
     nome VARCHAR(50) NOT NULL,
     email VARCHAR(50) NOT NULL,
     senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE TB_PERMISSAO (
       id BIGINT(20) PRIMARY KEY,
       descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE TB_USUARIO_PERMISSAO (
       id_usuario BIGINT(20) NOT NULL,
       id_permissao BIGINT(20) NOT NULL,
       PRIMARY KEY (id_usuario, id_permissao),
       FOREIGN KEY (id_usuario) REFERENCES TB_USUARIO(id),
       FOREIGN KEY (id_permissao) REFERENCES TB_PERMISSAO(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_USUARIO (id, nome, email, senha) values (1, 'Administrador', 'admin@zcsistemas.com.br', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO TB_USUARIO (id, nome, email, senha) values (2, 'Evelyn Brun', 'evelyn@zcsistemas.com.br', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO TB_PERMISSAO (id, descricao) values (1, 'ROLE_CADASTRAR_BANCO');
INSERT INTO TB_PERMISSAO (id, descricao) values (2, 'ROLE_PESQUISAR_BANCO');

INSERT INTO TB_PERMISSAO (id, descricao) values (3, 'ROLE_CADASTRAR_CARGO');
INSERT INTO TB_PERMISSAO (id, descricao) values (4, 'ROLE_REMOVER_CARGO');
INSERT INTO TB_PERMISSAO (id, descricao) values (5, 'ROLE_PESQUISAR_CARGO');

INSERT INTO TB_PERMISSAO (id, descricao) values (6, 'ROLE_CADASTRAR_CONTA_BANCARIA');
INSERT INTO TB_PERMISSAO (id, descricao) values (7, 'ROLE_REMOVER_CONTA_BANCARIA');
INSERT INTO TB_PERMISSAO (id, descricao) values (8, 'ROLE_PESQUISAR_CONTA_BANCARIA');

INSERT INTO TB_PERMISSAO (id, descricao) values (9, 'ROLE_CADASTRAR_TIPO_CADASTRO');
INSERT INTO TB_PERMISSAO (id, descricao) values (10, 'ROLE_REMOVER_TIPO_CADASTRO');
INSERT INTO TB_PERMISSAO (id, descricao) values (11, 'ROLE_PESQUISAR_TIPO_CADASTRO');

INSERT INTO TB_PERMISSAO (id, descricao) values (12, 'ROLE_CADASTRAR_TIPO_CONTAS');
INSERT INTO TB_PERMISSAO (id, descricao) values (13, 'ROLE_REMOVER_TIPO_CONTAS');
INSERT INTO TB_PERMISSAO (id, descricao) values (14, 'ROLE_TIPO_CONTAS');

-- admin
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 1);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 2);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 3);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 4);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 5);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 6);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 7);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 8);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 9);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 10);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 11);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 12);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 13);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (1, 14);

-- evelyn
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (2, 2);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (2, 5);
INSERT INTO TB_USUARIO_PERMISSAO (id_usuario, id_permissao) values (2, 8);
