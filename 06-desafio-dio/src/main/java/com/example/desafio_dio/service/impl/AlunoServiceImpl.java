package com.example.desafio_dio.service.impl;

import com.example.desafio_dio.entity.Aluno;
import com.example.desafio_dio.entity.AvaliacaoFisica;
import com.example.desafio_dio.entity.form.AlunoForm;
import com.example.desafio_dio.entity.form.AlunoUpdateForm;
import com.example.desafio_dio.repository.AlunoRepository;
import com.example.desafio_dio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
            aluno.setNome(form.getNome());
            aluno.setCpf(form.getCpf());
            aluno.setBairro(form.getBairro());
            aluno.setDataDeNascimento(form.getDataDeNascimento());

        return repository.save(aluno);
    }

    @Override
    public Aluno get(Long id) {
        return null;
    }

    @Override
    public List<Aluno> getAll() {
        return repository.findAll();
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpsdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisica() {
        repository.findAll();
        return List.of();
    }
}
