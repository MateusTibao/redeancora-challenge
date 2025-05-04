package br.com.challenge.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CompraEntidade")
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    @Getter
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itens = new ArrayList<>();

    @Getter
    private LocalDate data;
    @Getter
    private double valorTotal;
    @Getter
    private boolean finalizada;

}

