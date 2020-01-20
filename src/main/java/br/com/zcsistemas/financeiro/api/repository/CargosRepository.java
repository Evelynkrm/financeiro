package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.Cargos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargosRepository extends JpaRepository<Cargos, Long> {
}
