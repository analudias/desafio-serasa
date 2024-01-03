package com.serasa.desafio.desafioserasa.resources;

import com.serasa.desafio.desafioserasa.dto.EnderecoDTO;
import com.serasa.desafio.desafioserasa.entities.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EnderecoResource {

    @GetMapping(value = "/{cep}")
    public EnderecoDTO findCep(@PathVariable String cep) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(String.format("https://viacep.com.br/ws/%s/json", cep));
        EnderecoDTO dto = restTemplate.getForObject(uri, EnderecoDTO.class);

        EnderecoDTO enderecoAtualizado = new EnderecoDTO(
                dto.getCep(),
                dto.getLogradouro(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf()
        );

        return enderecoAtualizado;
    }


}
