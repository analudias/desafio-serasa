package com.serasa.desafio.desafioserasa.resources;

import com.serasa.desafio.desafioserasa.entities.Pessoa;
import com.serasa.desafio.desafioserasa.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<Pessoa>> findAll(Pageable pageable) {
        Page<Pessoa> list = pessoaService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa) throws URISyntaxException {
        Pessoa newPessoa = pessoaService.insert(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(newPessoa);
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<Pessoa> findByName(@PathVariable String nome) {
        Pessoa pessoa = pessoaService.findByNome(nome);
        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa entidade) {
        entidade = pessoaService.update(id, entidade);
        return ResponseEntity.ok().body(entidade);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/idade/{idade}")
    public ResponseEntity<Pessoa> findByIdade(@PathVariable int idade) {
        Pessoa pessoa = pessoaService.findByIdade(idade);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping(value = "/cep/{cep}")
    public ResponseEntity<Pessoa> findByCep(@PathVariable String cep) {
        Pessoa pessoa = pessoaService.findByCep(cep);
        return ResponseEntity.ok().body(pessoa);
    }

}
