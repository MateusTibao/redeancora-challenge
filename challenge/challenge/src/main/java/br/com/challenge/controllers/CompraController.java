package br.com.challenge.controllers;

import br.com.challenge.DTOs.CompraDTO;
import br.com.challenge.DTOs.ItemCompraDTO;
import br.com.challenge.models.Compra;
import br.com.challenge.models.ItemCompra;
import br.com.challenge.models.Oficina;
import br.com.challenge.models.Produto;
import br.com.challenge.services.CompraService;
import br.com.challenge.services.OficinaService;
import br.com.challenge.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private OficinaService oficinaService;

    @PostMapping("/criar")
    public CompraDTO criarCompra(@RequestParam Long oficinaId) {
        Compra novaCompra = new Compra();
        novaCompra.setData(LocalDate.now());
        novaCompra.setFinalizada(false);
        novaCompra.setValorTotal(0.0);
        novaCompra.setItens(new ArrayList<>());

        Oficina oficina = oficinaService.buscarOficinaPorId(oficinaId);
        novaCompra.setOficina(oficina);

        Compra salva = compraService.salvarCompra(novaCompra);
        return converterParaDTO(salva);
    }

    @PostMapping("/{id}/adicionar")
    public CompraDTO adicionarProduto(@PathVariable Long id,
                                      @RequestParam Long produtoId,
                                      @RequestParam int quantidade) {
        Optional<Compra> compraOpt = compraService.buscarCompraPorId(id);
        Optional<Produto> produtoOpt = produtoService.buscarProdutoPorId(produtoId);

        if (compraOpt.isPresent() && produtoOpt.isPresent()) {
            compraService.adicionarProduto(compraOpt.get(), produtoOpt.get(), quantidade);
            return converterParaDTO(compraOpt.get());
        }
        return null;
    }

    @PostMapping("/{id}/remover")
    public CompraDTO removerProduto(@PathVariable Long id,
                                    @RequestParam Long produtoId,
                                    @RequestParam int quantidade) {
        Optional<Compra> compraOpt = compraService.buscarCompraPorId(id);
        Optional<Produto> produtoOpt = produtoService.buscarProdutoPorId(produtoId);

        if (compraOpt.isPresent() && produtoOpt.isPresent()) {
            compraService.removerProduto(compraOpt.get(), produtoOpt.get(), quantidade);
            return converterParaDTO(compraOpt.get());
        }
        return null;
    }


    @PostMapping("/{id}/finalizar")
    public CompraDTO finalizarCompra(@PathVariable Long id) {
        Optional<Compra> compraOpt = compraService.buscarCompraPorId(id);

        if (compraOpt.isPresent()) {
            try {
                compraService.finalizarCompra(compraOpt.get());
                return converterParaDTO(compraOpt.get());
            } catch (RuntimeException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao finalizar a compra: " + e.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");
        }
    }

    private CompraDTO converterParaDTO(Compra compra) {
        List<ItemCompraDTO> itensDTO = new ArrayList<>();
        for (ItemCompra item : compra.getItens()) {
            ItemCompraDTO itemDTO = new ItemCompraDTO();
            itemDTO.setProdutoId(item.getProduto().getId());
            itemDTO.setProdutoNome(item.getProduto().getNome());
            itemDTO.setQuantidade(item.getQuantidade());
            itemDTO.setSubtotal(item.getSubtotal());
            itensDTO.add(itemDTO);
        }

        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        if (compra.getOficina() != null) {
            dto.setOficinaId(compra.getOficina().getId());
            dto.setOficinaNome(compra.getOficina().getNome());
        }
        dto.setData(compra.getData());
        dto.setValorTotal(compra.getValorTotal());
        dto.setFinalizada(compra.isFinalizada());
        dto.setItens(itensDTO);
        return dto;
    }
}
