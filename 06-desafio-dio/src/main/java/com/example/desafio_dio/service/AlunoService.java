package com.example.desafio_dio.service;

import com.example.desafio_dio.entity.Aluno;
import com.example.desafio_dio.entity.form.AlunoForm;
import com.example.desafio_dio.entity.form.AlunoUpdateForm;

import java.util.List;

public interface AlunoService {

    Aluno create(AlunoForm form);

    Aluno get(Long id);

    List<Aluno> getAll();

    Aluno update(Long id, AlunoUpdateForm formUpsdate);

    void delete(Long id);
}
