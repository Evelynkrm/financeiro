package br.com.zcsistemas.financeiro.api.resource;

import br.com.zcsistemas.financeiro.api.event.RecursoCriadoEvent;
import br.com.zcsistemas.financeiro.api.model.TipoContas;
import br.com.zcsistemas.financeiro.api.repository.TipoContasRepository;
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
@RequestMapping("/tipoContas")
public class TipoContasResource {

    @Autowired
    private TipoContasRepository tipoContasRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_CONTA') and #oauth2.hasScope('read')")
    public List<TipoContas> listar() {
        return tipoContasRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_CONTA') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoContas> criar(@Valid @RequestBody TipoContas tipoContas, HttpServletResponse response) {
        TipoContas tipoContaSalva = tipoContasRepository.save(tipoContas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoContaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoContaSalva);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_CONTA') and #oauth2.hasScope('read')")
    public ResponseEntity<TipoContas> buscarPeloId(@PathVariable Long id) {
        TipoContas tipoContas = tipoContasRepository.findOne(id);
        return tipoContas != null ? ResponseEntity.ok(tipoContas) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_CONTA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        tipoContasRepository.delete(id);
    }
}
