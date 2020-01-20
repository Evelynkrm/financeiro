package br.com.zcsistemas.financeiro.api.resource;

import br.com.zcsistemas.financeiro.api.event.RecursoCriadoEvent;
import br.com.zcsistemas.financeiro.api.model.ContasBancarias;
import br.com.zcsistemas.financeiro.api.repository.ContasBancariasRepository;
import br.com.zcsistemas.financeiro.api.repository.filter.ContasBancariasFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contasBancarias")
public class ContasBancariasResource {

    @Autowired
    private ContasBancariasRepository contasBancariasRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;


   /*  @GetMapping
   public List<ContasBancarias> listar() {
        return contasBancariasRepository.findAll();
    }*/

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTA_BANCARIA') and #oauth2.hasScope('read')")
    public Page<ContasBancarias> pesquisar(ContasBancariasFilter contasBancariasFilter, Pageable pageable) {
        return contasBancariasRepository.filtrar(contasBancariasFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTA_BANCARIA') and #oauth2.hasScope('read')")
    public ResponseEntity<ContasBancarias> buscarPeloId(@PathVariable Long id) {
        ContasBancarias contasBancarias = contasBancariasRepository.findOne(id);
        return contasBancarias != null ? ResponseEntity.ok(contasBancarias) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTA_BANCARIA') and #oauth2.hasScope('write')")
    public ResponseEntity<ContasBancarias> criar(@Valid @RequestBody ContasBancarias contasBancarias, HttpServletResponse response) {
        ContasBancarias contaBancariaSalva = contasBancariasRepository.save(contasBancarias);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, contaBancariaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaSalva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_CONTA_BANCARIA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        contasBancariasRepository.delete(id);
    }
/*
 @PutMapping("/{id}")
    public ResponseEntity<ContasBancarias> atualizar(@PathVariable Long id, @Valid @RequestBody ContasBancarias contasBancarias) {
     ContasBancarias bancoSalvo = contasBancariasService.atualizar(id, contasBancarias);
        return ResponseEntity.ok(bancoSalvo);
    }*/
}
