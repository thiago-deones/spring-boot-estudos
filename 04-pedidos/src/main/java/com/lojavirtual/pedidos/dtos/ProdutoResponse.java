package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidadeEstoque
) {
    public static ProdutoResponse fromEntity(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }
}
