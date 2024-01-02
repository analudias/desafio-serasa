package com.serasa.desafio.desafioserasa.services;

import com.serasa.desafio.desafioserasa.entities.Pessoa;
import com.serasa.desafio.desafioserasa.repositories.PessoaRepository;
import com.serasa.desafio.desafioserasa.resources.PessoaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Transactional(readOnly = true)
    public Page<Pessoa> findAllPaged(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }
}
