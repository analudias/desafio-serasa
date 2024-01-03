package com.serasa.desafio.desafioserasa.services;

import com.serasa.desafio.desafioserasa.dto.EnderecoDTO;
import com.serasa.desafio.desafioserasa.entities.Endereco;
import com.serasa.desafio.desafioserasa.entities.Pessoa;
import com.serasa.desafio.desafioserasa.gateway.EnderecoGateway;
import com.serasa.desafio.desafioserasa.repositories.PessoaRepository;
import com.serasa.desafio.desafioserasa.resources.PessoaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoGateway enderecoGateway;


    @Transactional(readOnly = true)
    public Page<Pessoa> findAllPaged(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa insert(Pessoa pessoa) throws URISyntaxException {
        Endereco endereco = new Endereco(enderecoGateway.findCep(pessoa.getEndereco().getCep()));

        pessoa.setEndereco(endereco);

        return pessoaRepository.save(pessoa);
    }
}
