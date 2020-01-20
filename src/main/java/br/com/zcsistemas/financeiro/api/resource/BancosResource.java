package br.com.zcsistemas.financeiro.api.resource;

import br.com.zcsistemas.financeiro.api.event.RecursoCriadoEvent;
import br.com.zcsistemas.financeiro.api.model.Bancos;
import br.com.zcsistemas.financeiro.api.repository.BancosRepository;
import br.com.zcsistemas.financeiro.api.service.BancosService;
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
@RequestMapping("/bancos")
public class BancosResource {

    @Autowired
    private BancosRepository bancosRepository;

    @Autowired
    private BancosService bancosService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_BANCO') and #oauth2.hasScope('read')")
    public List<Bancos> listar() {
        return bancosRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<Bancos> buscarPeloId(@PathVariable Long id) {
        Bancos bancos = bancosRepository.findOne(id);
        return bancos != null ? ResponseEntity.ok(bancos) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<Bancos> criar(@Valid @RequestBody Bancos bancos, HttpServletResponse response) {
        Bancos bancoSalvo = bancosRepository.save(bancos);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, bancoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoSalvo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_BANCO') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        bancosRepository.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<Bancos> atualizar(@PathVariable Long id, @Valid @RequestBody Bancos bancos) {
        Bancos bancoSalvo = bancosService.atualizar(id, bancos);
        return ResponseEntity.ok(bancoSalvo);
    }
}
