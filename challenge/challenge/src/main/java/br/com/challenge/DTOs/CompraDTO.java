package br.com.challenge.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    private Long id;
    private Long oficinaId;
    private String oficinaNome;
    private List<ItemCompraDTO> itens;
    private LocalDate data;
    private double valorTotal;
    private boolean finalizada;
}

