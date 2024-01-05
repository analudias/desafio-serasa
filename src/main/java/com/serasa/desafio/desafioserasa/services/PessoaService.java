package com.serasa.desafio.desafioserasa.services;

import com.serasa.desafio.desafioserasa.dto.EnderecoDTO;
import com.serasa.desafio.desafioserasa.entities.Endereco;
import com.serasa.desafio.desafioserasa.entities.Pessoa;
import com.serasa.desafio.desafioserasa.gateway.EnderecoGateway;
import com.serasa.desafio.desafioserasa.repositories.PessoaRepository;
import com.serasa.desafio.desafioserasa.resources.PessoaResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoGateway enderecoGateway;


    @Transactional(readOnly = true)
    public Page<Pessoa> findAllPaged(Pageable pageable) {
        Page<Pessoa> pessoasPage = pessoaRepository.findAll(pageable);
        pessoasPage.forEach(pessoa -> pessoa.setDescricaoScore(obterDescricaoScore(pessoa.getScore())));
        return pessoasPage;
    }

    public Pessoa insert(Pessoa pessoa) throws URISyntaxException {
        Endereco endereco = new Endereco(enderecoGateway.findCep(pessoa.getEndereco().getCep()));

        pessoa.setEndereco(endereco);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa findByNome(String nome) {
        Pessoa pessoa = pessoaRepository.findByNomeContainingIgnoreCase(nome);
        pessoa.setDescricaoScore(obterDescricaoScore(pessoa.getScore()));
        return pessoa;
    }

    public Pessoa findByIdade(int idade) {
        Pessoa pessoa = pessoaRepository.findByIdadeContainingIgnoreCase(idade);
        pessoa.setDescricaoScore(obterDescricaoScore(pessoa.getScore()));
        return pessoa;
    }

    public Pessoa findByCep(String cep) {
        Pessoa pessoa = pessoaRepository.findByCepContainingIgnoreCase(cep);
        pessoa.setDescricaoScore(obterDescricaoScore(pessoa.getScore()));
        return pessoa;
    }

    @Transactional
    public Pessoa update(Long id, Pessoa entidade) {
        try {
            Pessoa entity = pessoaRepository.getReferenceById(id);

            entity.setNome(entidade.getNome());
            entity.setIdade(entidade.getIdade());
            entity.setEmail(entidade.getEmail());
            entity.setEndereco(entidade.getEndereco());
            entity.setPerfis(entidade.getPerfis());
            entity.setTelefone(entidade.getTelefone());
            entity.setScore(entidade.getScore());

            return pessoaRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
    }

    public void delete(Long id) throws Exception {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
        try {
            pessoaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new Exception("Falha de integridade referencial");
        }
    }

    public String obterDescricaoScore(int score) {
        if (score >= 0 && score <= 200) {
            return "Insuficiente";
        } else if (score >= 201 && score <= 500) {
            return "Inaceitável";
        } else if (score >= 501 && score <= 700) {
            return "Aceitável";
        } else if (score >= 701 && score <= 1000) {
            return "Recomendável";
        } else {
            return "Score fora do intervalo especificado";
        }
    }
}
