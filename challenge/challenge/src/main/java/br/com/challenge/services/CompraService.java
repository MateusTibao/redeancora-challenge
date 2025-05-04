package br.com.challenge.services;

import br.com.challenge.models.Compra;
import br.com.challenge.models.ItemCompra;
import br.com.challenge.models.Produto;
import br.com.challenge.repositories.CompraRepository;
import br.com.challenge.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Compra salvarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public Optional<Compra> buscarCompraPorId(Long id) {
        return compraRepository.findById(id);
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public void excluirCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public void finalizarCompra(Compra compra) {
        if (compra.isFinalizada()) {
            throw new IllegalStateException("A compra já está finalizada.");
        }

        double valorTotal = calcularValorTotal(compra);
        compra.setValorTotal(valorTotal);
        compra.setFinalizada(true);
        compraRepository.save(compra);
    }

    public double calcularValorTotal(Compra compra) {
        double total = 0;
        for (ItemCompra item : compra.getItens()) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarProduto(Compra compra, Produto produto, int quantidade) {
        if (compra.isFinalizada()) {
            throw new IllegalStateException("Não é possível modificar uma compra finalizada.");
        }

        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        for (ItemCompra item : compra.getItens()) {
            if (item.getProduto().getId().equals(produto.getId())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                produto.setEstoque(produto.getEstoque() - quantidade);
                produtoRepository.save(produto);
                compra.setValorTotal(calcularValorTotal(compra));
                compraRepository.save(compra);
                return;
            }
        }

        ItemCompra itemCompra = new ItemCompra(produto, quantidade, compra);
        compra.getItens().add(itemCompra);
        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);
        compra.setValorTotal(calcularValorTotal(compra));
        compraRepository.save(compra);
    }

    public void removerProduto(Compra compra, Produto produto, int quantidade) {
        if (compra.isFinalizada()) {
            throw new IllegalStateException("Não é possível modificar uma compra finalizada.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a ser removida deve ser maior que zero.");
        }

        Iterator<ItemCompra> iterator = compra.getItens().iterator();
        while (iterator.hasNext()) {
            ItemCompra item = iterator.next();
            if (item.getProduto().getId().equals(produto.getId())) {
                if (quantidade >= item.getQuantidade()) {
                    produto.setEstoque(produto.getEstoque() + item.getQuantidade());
                    iterator.remove();
                } else {
                    item.setQuantidade(item.getQuantidade() - quantidade);
                    produto.setEstoque(produto.getEstoque() + quantidade);
                }
                produtoRepository.save(produto);
                compra.setValorTotal(calcularValorTotal(compra));
                compraRepository.save(compra);
                return;
            }
        }

        throw new IllegalArgumentException("Produto não encontrado na compra.");
    }

}
