package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "FuncionarioEntidade")
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Funcionario extends Pessoa {

    @Column(nullable = false, unique = true)
    private String funcional;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

    public Funcionario(String nome, String telefone, String cpf) {
        super(nome, telefone, cpf);
    }
}
