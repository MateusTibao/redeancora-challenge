package br.com.challenge.services;

import br.com.challenge.models.ItemCompra;
import br.com.challenge.repositories.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCompraService {

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public ItemCompra salvarItemCompra(ItemCompra itemCompra) {
        return itemCompraRepository.save(itemCompra);
    }

    public Optional<ItemCompra> buscarItemCompraPorId(Long id) {
        return itemCompraRepository.findById(id);
    }

    public List<ItemCompra> listarTodos() {
        return itemCompraRepository.findAll();
    }

    public void excluirItemCompra(Long id) {
        itemCompraRepository.deleteById(id);
    }

    public List<ItemCompra> buscarPorCompraId(Long idCompra) {
        return itemCompraRepository.findItemCompraByCompraId(idCompra);
    }

    public List<ItemCompra> buscarPorProdutoId(Long idProduto) {
        return itemCompraRepository.findItemCompraByProdutoId(idProduto);
    }

    public List<ItemCompra> buscarPorNomeProduto(String nomeProduto) {
        return itemCompraRepository.findItemCompraByProdutoNomeContainingIgnoreCase(nomeProduto);
    }
}
