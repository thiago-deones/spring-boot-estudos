package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.enums.FormaPagamento;
import com.lojavirtual.pedidos.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(

       Long id,
       Long clienteId,
       List<ItemPedidoResponse> itens,
       LocalDateTime dataCriacao,
       FormaPagamento formaPagamento,
       StatusPedido statusPedido,
       BigDecimal valorTotal

) {
}
