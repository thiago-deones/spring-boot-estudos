package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Produto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O limete é 100 caracteres")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O valor não pode ser negativo")
        BigDecimal preco,

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "O valor não pode ser negativo ")
        Integer quantidadeEstoque

) {

    public void preencher(Produto produto) {
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidadeEstoque(quantidadeEstoque);
    }
}
