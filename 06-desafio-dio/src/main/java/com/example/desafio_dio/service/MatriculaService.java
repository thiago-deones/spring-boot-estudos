package com.example.desafio_dio.service;

import com.example.desafio_dio.entity.Matricula;
import com.example.desafio_dio.entity.form.MatriculaForm;

import java.util.List;

public interface MatriculaService {

    Matricula create(MatriculaForm form);

    Matricula get(Long id);

    List<Matricula> getAll();

    void delete(Long id);
}
