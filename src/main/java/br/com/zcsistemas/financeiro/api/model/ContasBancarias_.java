package br.com.zcsistemas.financeiro.api.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContasBancarias.class)
public abstract class ContasBancarias_ {
    public static volatile SingularAttribute<ContasBancarias, Long> id;
    public static volatile SingularAttribute<ContasBancarias, String> descricao;
    public static volatile SingularAttribute<ContasBancarias, String> agencia;
    public static volatile SingularAttribute<ContasBancarias, String> conta;
    public static volatile SingularAttribute<ContasBancarias, String> observacoes;
    public static volatile SingularAttribute<ContasBancarias, Bancos> bancos;
    public static volatile SingularAttribute<ContasBancarias, TipoContas> tipoContas;


    public ContasBancarias_() {
    }
}
