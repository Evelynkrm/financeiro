package br.com.zcsistemas.financeiro.api.repository.contasBancarias;

import br.com.zcsistemas.financeiro.api.model.ContasBancarias;
import br.com.zcsistemas.financeiro.api.repository.filter.ContasBancariasFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContasBancariasRepositoryQuery {

    public Page<ContasBancarias> filtrar(ContasBancariasFilter contasBancariasFilter, Pageable pageable);
}
