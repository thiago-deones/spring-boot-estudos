package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.enums.FormaPagamento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record PedidoRequest(

        @NotNull
        Long clienteId,

        @NotNull
        @NotEmpty
        List<ItemPedidoRequest> itens,

        FormaPagamento formaPagamento

) {
}
