package br.com.challenge.controllers;

import br.com.challenge.DTOs.ProdutoDTO;
import br.com.challenge.models.Produto;
import br.com.challenge.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> dtos = new ArrayList<>();

        for (Produto produto : produtos) {
            dtos.add(converterParaDTO(produto));
        }

        return dtos;
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return converterParaDTO(produto.get());
        } else {
            return null;
        }
    }

    private ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        );
    }
}
