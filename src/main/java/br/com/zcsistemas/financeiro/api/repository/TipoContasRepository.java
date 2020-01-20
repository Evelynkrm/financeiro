package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.TipoContas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoContasRepository extends JpaRepository<TipoContas, Long> {
}
