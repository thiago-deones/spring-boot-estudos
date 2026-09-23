package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String cpf
) {


    public static ClienteResponse fromEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf()
        );
    }

}
