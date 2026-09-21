package com.ebook.__livraria.exception;

import java.time.LocalDateTime;

public record ErroResposta(
        int status,
        String erro,
        LocalDateTime timestamp
) {
    public static ErroResposta de(int status, String erro, String mensagem) {
        return new ErroResposta(status, mensagem, LocalDateTime.now());
    }

}
