package br.com.challenge.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCompraDTO {
    private Long id;
    private Long produtoId;
    private String produtoNome;
    private int quantidade;
    private double precoUnitario; // Preço do produto na hora da compra
    private double subtotal; // Quantidade * Preço Unitário
}
