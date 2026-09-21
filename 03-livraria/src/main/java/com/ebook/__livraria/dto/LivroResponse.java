package com.ebook.__livraria.dto;

import com.ebook.__livraria.domain.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        String isbn,
        BigDecimal preco,
        LocalDate dataPublicacao
) {
    public static LivroResponse fromEntity(Livro livro){
        if (livro == null) {
            return null;
        }
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getDataPublicacao()
        );
    }
}
