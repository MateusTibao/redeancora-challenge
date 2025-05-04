package br.com.challenge.models;

import jakarta.persistence.*;
import br.com.challenge.models.Produto;
import lombok.*;

@Entity(name = "ItemCompraEntidade")
@Table(name = "itens_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public ItemCompra(Produto produto, int quantidade, Compra compra) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.compra = compra;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
}
