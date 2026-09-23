package com.lojavirtual.pedidos.dtos;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String cpf
) {
}
