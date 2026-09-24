package com.lojavirtual.pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.domain.Pedido;
import com.lojavirtual.pedidos.domain.Produto;
import com.lojavirtual.pedidos.dtos.ClienteResponse;
import com.lojavirtual.pedidos.dtos.PedidoRequest;
import com.lojavirtual.pedidos.dtos.PedidoResponse;
import com.lojavirtual.pedidos.dtos.ProdutoResponse;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import com.lojavirtual.pedidos.repository.PedidoRepository;
import com.lojavirtual.pedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoResponse cadastrar(PedidoRequest pedidoRequest) {

        Cliente cliente = buscarClientePorId(pedidoRequest.clienteId());

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
