package br.com.zcsistemas.financeiro.api.repository;

import br.com.zcsistemas.financeiro.api.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    public Optional<Usuarios> findByEmail(String email);

}
