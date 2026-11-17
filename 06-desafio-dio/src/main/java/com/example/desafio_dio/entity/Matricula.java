package com.example.desafio_dio.entity;

import java.time.LocalDateTime;

public class Matricula {

    private Long id;
    private Aluno aluno;
    private LocalDateTime dataMatricula = LocalDateTime.now();
}
