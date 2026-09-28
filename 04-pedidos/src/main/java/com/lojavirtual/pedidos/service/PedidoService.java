package com.lojavirtual.pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.domain.ItemPedido;
import com.lojavirtual.pedidos.domain.Pedido;
import com.lojavirtual.pedidos.domain.Produto;
import com.lojavirtual.pedidos.domain.enums.FormaPagamento;
import com.lojavirtual.pedidos.domain.enums.StatusPedido;
import com.lojavirtual.pedidos.dtos.*;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import com.lojavirtual.pedidos.repository.PedidoRepository;
import com.lojavirtual.pedidos.repository.ProdutoRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoResponse cadastrar(PedidoRequest pedidoRequest) {

        Cliente cliente = buscarClientePorId(pedidoRequest.clienteId());

        List<ItemPedidoRequest> itens = pedidoRequest.itens();

        for (ItemPedidoRequest item : pedidoRequest.itens()) {
            Produto produto = buscarProdutoPorId(item.produtoId());

            Integer quantidade = item.quantidade();

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);
        }


    }

    public Pageable<PedidoRequest> listar(Pageable pageable) {
        return pedidoRepository;
    }

    public ProdutoRepository buscarPedidoId(Long id) {
        Pedido pedido = buscarPedidoPorId(id);
        return pedidoRepository.fromEntidy(pedido);
    }

    public void deletar(Long id) {
        Pedido pedido = buscarPedidoPorId(id);
        pedidoRepository.delete(pedido);
    }

    public PedidoResponse atualizar(Long id, PedidoRequest request) {

        Pedido pedido = buscarPedidoPorId(id);

        request.preencher(pedido);

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return PedidoResponse.fromEntity(pedidoAtualizado);
    }

    private Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente não existe com este ID: " + id
                ));
    }

    private Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Produto não existe com este ID: " + id
                ));
    }

    private Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pedido não existe com este ID: " + id
                ));
    }
}
