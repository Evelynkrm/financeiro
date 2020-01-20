package br.com.zcsistemas.financeiro.api.resource;

import br.com.zcsistemas.financeiro.api.event.RecursoCriadoEvent;
import br.com.zcsistemas.financeiro.api.model.Cargos;
import br.com.zcsistemas.financeiro.api.repository.CargosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargosResource {

    @Autowired
    private CargosRepository cargosRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARGO') and #oauth2.hasScope('read')")
    public List<Cargos> listar() {
        return cargosRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CARGO') and #oauth2.hasScope('write')")
    public ResponseEntity<Cargos> criar(@Valid @RequestBody Cargos cargos, HttpServletResponse response) {
        Cargos cargoSalvo = cargosRepository.save(cargos);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, cargoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoSalvo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARGO') and #oauth2.hasScope('read')")
    public ResponseEntity<Cargos> buscarPeloId(@PathVariable Long id) {
        Cargos cargos = cargosRepository.findOne(id);
        return cargos != null ? ResponseEntity.ok(cargos) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_CARGO') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cargosRepository.delete(id);
    }
}
