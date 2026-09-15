package dev.thiago.mastersys.dto;

public record AlunoFiltroRequest(
        String nome,
        String email,
        String celular,
        String cidade,
        String estado
) {
}
