CREATE TABLE TB_BANCO (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_BANCO (nome) values ('001 - Banco do Brasil S.A.');