package br.com.challenge.repositories;

import br.com.challenge.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoById(Long id);

    List<Produto> findProdutoByNomeContainingIgnoreCase(String nome);

    List<Produto> findProdutoByDescricaoContainingIgnoreCase(String descricao);

    List<Produto> findProdutoByEstoqueLessThanEqual(int quantidade);

    List<Produto> findProdutoByPrecoBetween(double precoMin, double precoMax);
}

