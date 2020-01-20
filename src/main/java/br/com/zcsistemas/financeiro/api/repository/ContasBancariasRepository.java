package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.ContasBancarias;
import br.com.zcsistemas.financeiro.api.repository.contasBancarias.ContasBancariasRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasBancariasRepository extends JpaRepository<ContasBancarias, Long>, ContasBancariasRepositoryQuery {
}
