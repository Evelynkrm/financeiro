package br.com.zcsistemas.financeiro.api.resource;

import br.com.zcsistemas.financeiro.api.event.RecursoCriadoEvent;
import br.com.zcsistemas.financeiro.api.model.TipoCadastros;
import br.com.zcsistemas.financeiro.api.repository.TipoCadastrosRepository;
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
@RequestMapping("/tipoCadastros")
public class TipoCadastrosResource {

    @Autowired
    private TipoCadastrosRepository tipoCadastrosRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_CADASTRO') and #oauth2.hasScope('read')")
    public List<TipoCadastros> listar() {
        return tipoCadastrosRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TIPO_CADASTRO') and #oauth2.hasScope('read')")
    public ResponseEntity<TipoCadastros> criar(@Valid @RequestBody TipoCadastros tipoCadastros, HttpServletResponse response) {
        TipoCadastros tipoCadastroSalvo = tipoCadastrosRepository.save(tipoCadastros);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoCadastroSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoCadastroSalvo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_CADASTRO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoCadastros> buscarPeloId(@PathVariable Long id) {
        TipoCadastros tipoCadastros = tipoCadastrosRepository.findOne(id);
        return tipoCadastros != null ? ResponseEntity.ok(tipoCadastros) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TIPO_CADASTRO') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        tipoCadastrosRepository.delete(id);
    }
}