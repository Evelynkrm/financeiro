CREATE TABLE TB_TIPO_CONTA (
      id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
      descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_TIPO_CONTA (descricao) values ('Conta Salário');