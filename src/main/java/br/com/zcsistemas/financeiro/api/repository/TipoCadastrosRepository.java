package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.TipoCadastros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCadastrosRepository extends JpaRepository<TipoCadastros, Long> {
}
