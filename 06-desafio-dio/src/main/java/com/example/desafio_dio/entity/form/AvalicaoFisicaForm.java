package com.example.desafio_dio.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvalicaoFisicaForm {

    private Long alunoId;
    private double peso;
    private double altura;
}
