package br.com.zcsistemas.financeiro.api.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CONTA_BANCARIA", schema = "public")
@Data
public class ContasBancarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private String agencia;

    @NotNull
    private String conta;

    private String observacoes;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_banco")
    private Bancos bancos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_conta")
    private TipoContas tipoContas;

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public String getConta() { return conta; }
    public void setConta(String conta) { this.conta = conta; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Bancos getBancos() { return bancos; }
    public void setBancos(Bancos bancos) { this.bancos = bancos; }
    public TipoContas getTipoContas() { return tipoContas; }
    public void setTipoContas(TipoContas tipoContas) { this.tipoContas = tipoContas; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContasBancarias other = (ContasBancarias) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
