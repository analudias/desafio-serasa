package com.serasa.desafio.desafioserasa.resources;

import com.serasa.desafio.desafioserasa.dto.EnderecoDTO;
import com.serasa.desafio.desafioserasa.entities.Endereco;
import com.serasa.desafio.desafioserasa.gateway.EnderecoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EnderecoResource {

    @Autowired
    private EnderecoGateway enderecoGateway;

    @GetMapping(value = "/{cep}")
    public ResponseEntity<EnderecoDTO> findCep(@PathVariable String cep) throws URISyntaxException {
        EnderecoDTO dto = enderecoGateway.findCep(cep);
        return ResponseEntity.ok(dto);
    }

}
