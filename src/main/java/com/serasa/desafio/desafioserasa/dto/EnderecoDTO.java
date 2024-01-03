package com.serasa.desafio.desafioserasa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String cep;
    private String uf;
    private String localidade;
    private String bairro;
    private String logradouro;

}
