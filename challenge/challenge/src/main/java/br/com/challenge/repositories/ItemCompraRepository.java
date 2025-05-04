package br.com.challenge.repositories;

import br.com.challenge.models.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {

    List<ItemCompra> findItemCompraByCompraId(Long idCompra);

    List<ItemCompra> findItemCompraByProdutoId(Long idProduto);

    List<ItemCompra> findItemCompraByProdutoNomeContainingIgnoreCase(String nomeProduto);

}
