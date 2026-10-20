package com.lojavirtual.__pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.domain.Pedido;
import com.lojavirtual.pedidos.domain.Produto;
import com.lojavirtual.pedidos.domain.enums.FormaPagamento;
import com.lojavirtual.pedidos.dtos.ItemPedidoRequest;
import com.lojavirtual.pedidos.dtos.PedidoRequest;
import com.lojavirtual.pedidos.dtos.PedidoResponse;
import com.lojavirtual.pedidos.exception.RecursoNaoEncontradoException;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import com.lojavirtual.pedidos.repository.PedidoRepository;
import com.lojavirtual.pedidos.repository.ProdutoRepository;
import com.lojavirtual.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deveCadastrarPedidoComSucesso() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Teclado");
        produto.setPreco(new BigDecimal("100.00"));
        produto.setQuantidadeEstoque(10);

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        ItemPedidoRequest itemRequest = new ItemPedidoRequest(
                1L,
                2
        );

        PedidoRequest pedidoRequest = new PedidoRequest(
                1L,
                List.of(itemRequest),
                FormaPagamento.PIX
        );

        when(pedidoRepository.save(any(Pedido.class)))
                .thenAnswer(invocation -> {
                    Pedido pedido = invocation.getArgument(0);
                    pedido.setId(1L);
                    return pedido;
                });

        PedidoResponse response = pedidoService.cadastrar(pedidoRequest);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(1L, response.clienteId());
        assertEquals(1, response.itens().size());
        assertEquals(new BigDecimal("200.00"), response.valorTotal());

        assertEquals(8, produto.getQuantidadeEstoque());

        verify(clienteRepository).findById(1L);
        verify(produtoRepository).findById(1L);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {

        ItemPedidoRequest itemPedidoRequest = new ItemPedidoRequest(
                1L,
                2
        );

        PedidoRequest pedidoRequest = new PedidoRequest(
                1l,
                List.of(itemPedidoRequest),
                FormaPagamento.PIX
        );

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> pedidoService.cadastrar(pedidoRequest);
        );

        verify(clienteRepository).findById(1L);
        verifyNoInteractions(produtoRepository);
        verifyNoInteractions(pedidoRepository);
    }
}
