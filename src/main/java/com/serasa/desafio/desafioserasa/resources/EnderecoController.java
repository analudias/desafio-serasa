package com.serasa.desafio.desafioserasa.resources;

import com.serasa.desafio.desafioserasa.entities.Endereco;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EnderecoController {

    @GetMapping(value = "/{cep}")
    public Endereco findCep(@PathVariable String cep) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(String.format("https://viacep.com.br/ws/%s/json", cep));
        Endereco entity = restTemplate.getForObject(uri, Endereco.class);

        return entity;
    }


}
