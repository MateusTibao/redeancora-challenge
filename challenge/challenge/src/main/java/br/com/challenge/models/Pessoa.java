package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "PessoaEntidade")
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // geração automática do ID
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String cpf;

    public Pessoa(String nome, String telefone, String cpf) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}