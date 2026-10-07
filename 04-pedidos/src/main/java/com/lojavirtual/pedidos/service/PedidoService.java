package com.lojavirtual.pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.domain.ItemPedido;
import com.lojavirtual.pedidos.domain.Pedido;
import com.lojavirtual.pedidos.domain.Produto;
import com.lojavirtual.pedidos.domain.enums.StatusPedido;
import com.lojavirtual.pedidos.dtos.*;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import com.lojavirtual.pedidos.repository.PedidoRepository;
import com.lojavirtual.pedidos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public PedidoResponse cadastrar(PedidoRequest pedidoRequest) {

        Cliente cliente = buscarClientePorId(pedidoRequest.clienteId());

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);


        for (ItemPedidoRequest item : pedidoRequest.itens()) {

            Produto produto = buscarProdutoPorId(item.produtoId());

            Integer quantidade = item.quantidade();

            if (item.quantidade() > produto.getQuantidadeEstoque()) {
                throw new RuntimeException("Estoque insuficiente quantidade disponive: " + produto.getQuantidadeEstoque());
            }

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setPrecoUnitario(produto.getPreco());

            itemPedido.setPedido(pedido);

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() - quantidade
            );

            pedido.getItens().add(itemPedido);
        }

        pedido.setFormaPagamento(pedidoRequest.formaPagamento());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        List<ItemPedidoResponse> itensResponse = pedidoSalvo.getItens()
                .stream()
                .map(this::criarItemResponse)
                .toList();

        BigDecimal valorTotal = itensResponse.stream()
                .map(ItemPedidoResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PedidoResponse(
                pedidoSalvo.getId(),
                pedidoSalvo.getCliente().getId(),
                itensResponse,
                pedidoSalvo.getDataCriacao(),
                pedidoSalvo.getFormaPagamento(),
                pedidoSalvo.getStatus(),
                valorTotal
        );
    }

    public Page<PedidoResponse> listar(Pageable pageable) {
        return pedidoRepository.findAll(pageable)
                .map(this::criarPedidoResponse);

    }

    public PedidoResponse buscarPorId(Long id) {

        Pedido pedido = buscarPedidoPorId(id);
        return criarPedidoResponse(pedido);
    }

//    @Transactional
//    public void deletar(Long id) {
//
//        Pedido pedido = buscarPedidoPorId(id);
//
//        if (pedido.getStatus() != StatusPedido.ABERTO) {
//            throw new RuntimeException(
//                    "Só é possível excluir pedidos em aberto"
//            );
//        }
//
//        for (ItemPedido item : pedido.getItens()) {
//
//            Produto produto = item.getProduto();
//
//            produto.setQuantidadeEstoque(
//                    produto.getQuantidadeEstoque() + item.getQuantidade()
//            );
//
//        }
//
//        pedidoRepository.delete(pedido);
//    }


    @Transactional
    public PedidoResponse cancelar(Long id) {

        Pedido pedido = buscarPedidoPorId(id);

        if (pedido.getStatus() == StatusPedido.FINALIZADO
                || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RuntimeException(
                    "Pedido não pode ser cancelado. Motivo : ou ele ja foi cancelado ou finalizado."
            );
        }

        for (ItemPedido item : pedido.getItens()) {

            Produto produto = item.getProduto();

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() + item.getQuantidade()
            );

            pedido.setStatus(StatusPedido.CANCELADO);
        }

        return criarPedidoResponse(pedido);
    }
//    public PedidoResponse atualizar(Long id, PedidoRequest request) {
//
//        Pedido pedido = buscarPedidoPorId(id);
//
//        request.preencher(pedido);
//
//        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
//
//        return PedidoResponse.fromEntity(pedidoAtualizado);
//    }

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

    private ItemPedidoResponse criarItemResponse(ItemPedido item) {

        BigDecimal subtotal = item.getPrecoUnitario()
                .multiply(BigDecimal.valueOf(item.getQuantidade()));

        return new ItemPedidoResponse(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                subtotal
        );
    }

    private PedidoResponse criarPedidoResponse(Pedido pedido) {

        List<ItemPedidoResponse> itensResponse = pedido.getItens()
                .stream()
                .map(this::criarItemResponse)
                .toList();

        BigDecimal valorTotal = itensResponse.stream()
                .map(ItemPedidoResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                itensResponse,
                pedido.getDataCriacao(),
                pedido.getFormaPagamento(),
                pedido.getStatus(),
                valorTotal
        );
    }
}
