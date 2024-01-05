package com.serasa.desafio.desafioserasa.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa")
@EqualsAndHashCode(of = "id")
public class Pessoa{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    @JsonIgnoreProperties("pessoa")
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_pessoa_perfil",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<Perfil> perfis = new HashSet<>();

    private String telefone;
    private int score;

    private String senha;

}
