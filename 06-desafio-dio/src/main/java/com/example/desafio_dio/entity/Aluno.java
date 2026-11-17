package com.example.desafio_dio.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private Long id;
    private String nome;
    private String cpf;
    private String bairro;
    private LocalDate dataDeNascimento;
    private List<AvaliacaoFisica> avalicacoes = new ArrayList<>();
}
