package com.serasa.desafio.desafioserasa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serasa.desafio.desafioserasa.dto.EnderecoDTO;
import com.serasa.desafio.desafioserasa.repositories.PessoaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String uf;
    private String localidade;
    private String bairro;
    private String logradouro;

    @OneToOne(mappedBy = "endereco", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("endereco")
    private Pessoa pessoa;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.getCep();
        this.uf = enderecoDTO.getUf();
        this.localidade = enderecoDTO.getLocalidade();
        this.bairro = enderecoDTO.getBairro();
        this.logradouro = enderecoDTO.getLogradouro();
    }

}
