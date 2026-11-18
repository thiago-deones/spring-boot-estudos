package com.example.desafio_dio.entity.form;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

    private String nome;
    private String cpf;
    private String  bairro;
    private LocalDate dataDeNascimento;
}
