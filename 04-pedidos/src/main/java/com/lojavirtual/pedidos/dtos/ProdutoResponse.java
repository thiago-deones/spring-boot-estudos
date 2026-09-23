package com.lojavirtual.pedidos.dtos;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidadeEstoque,
        Boolean ativo
) {
}
