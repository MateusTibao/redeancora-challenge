package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "GerenteEntidade")
@Table(name = "gerentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Gerente extends Pessoa {

    @Column(nullable = false, unique = true)
    private String funcional;

    @OneToMany(mappedBy = "gerente", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public Gerente(String nome, String telefone, String cpf) {
        super(nome, telefone, cpf);
    }
}
