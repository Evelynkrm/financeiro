CREATE TABLE TB_CONTA_BANCARIA (
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   descricao VARCHAR(30) NOT NULL,
   agencia VARCHAR(10) NOT NULL,
   conta VARCHAR(15) NOT NULL,
   observacoes VARCHAR(150),
   id_banco BIGINT(20) NOT NULL,
   id_tipo_conta BIGINT(20) NOT NULL,
   FOREIGN KEY (id_banco) REFERENCES TB_BANCO(id),
   foreign key (id_tipo_conta) references TB_TIPO_CONTA(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TB_CONTA_BANCARIA (descricao,agencia,conta, observacoes, id_banco,id_tipo_conta) values ('Conta Evelyn', '06861', '27745-9',null,2,2);