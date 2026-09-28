package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoRequest(

        @NotNull
        Long produtoId,

        @NotNull
        @Positive
        Integer quantidade
) {
}
