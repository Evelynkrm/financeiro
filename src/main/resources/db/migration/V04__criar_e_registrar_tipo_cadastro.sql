CREATE TABLE TB_TIPO_CADASTRO (
      id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
      nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_TIPO_CADASTRO (nome) values ('Teste');