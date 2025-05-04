package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ProdutoEntidade")
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private int estoque;

    public Produto(String nome, String descricao, double preco, int estoque) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }
}

