package com.serasa.desafio.desafioserasa.repositories;

import com.serasa.desafio.desafioserasa.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
