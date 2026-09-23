package com.lojavirtual.pedidos.dtos;

import com.lojavirtual.pedidos.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter limite de 100 caracteres")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email invalido")
        @Size(max = 100, message = "O email tem de ter no máxímo 100 caracteres")
        String email,

        @NotBlank(message = "O cpf é obrigatório")
        @Size(max = 14, message = "Deve ter no máximo 14 caracters")
        String cpf

) {

    public void preencher(Cliente cliente) {
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
    }
}
