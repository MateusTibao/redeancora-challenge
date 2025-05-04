package br.com.challenge.services;

import br.com.challenge.models.Produto;
import br.com.challenge.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findProdutoById(id);
    }

    public List<Produto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findProdutoByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> buscarProdutoPorDescricao(String descricao) {
        return produtoRepository.findProdutoByDescricaoContainingIgnoreCase(descricao);
    }

    public List<Produto> buscarProdutosComEstoqueAte(int quantidade) {
        return produtoRepository.findProdutoByEstoqueLessThanEqual(quantidade);
    }

    public List<Produto> buscarProdutosPorFaixaDePreco(double precoMin, double precoMax) {
        return produtoRepository.findProdutoByPrecoBetween(precoMin, precoMax);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
