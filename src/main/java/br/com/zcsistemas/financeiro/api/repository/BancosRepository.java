package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.Bancos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancosRepository extends JpaRepository<Bancos, Long> {
}
