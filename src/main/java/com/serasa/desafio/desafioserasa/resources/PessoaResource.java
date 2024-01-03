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

}
