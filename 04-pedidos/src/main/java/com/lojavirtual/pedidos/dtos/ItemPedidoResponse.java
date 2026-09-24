package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Pedido;
import com.lojavirtual.pedidos.domain.Produto;

import java.math.BigDecimal;
import java.util.List;

public record ItemPedidoResponse(
        Long id,
        String nomeProduto,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}
