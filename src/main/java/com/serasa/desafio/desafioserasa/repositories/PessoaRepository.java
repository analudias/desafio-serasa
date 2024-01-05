package com.serasa.desafio.desafioserasa.repositories;

import com.serasa.desafio.desafioserasa.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByNomeContainingIgnoreCase(String nome);
    @Query("SELECT p FROM Pessoa p WHERE p.idade = :idade")
    Pessoa findByIdadeContainingIgnoreCase(int idade);
    @Query("SELECT p FROM Pessoa p WHERE p.endereco.cep = :cep")
    Pessoa findByCepContainingIgnoreCase(@Param("cep") String cep);

}
