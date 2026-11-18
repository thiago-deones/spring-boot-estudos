package com.example.desafio_dio.service.impl;

import com.example.desafio_dio.entity.Aluno;
import com.example.desafio_dio.entity.AvaliacaoFisica;
import com.example.desafio_dio.entity.form.AvaliacaoUpdateForm;
import com.example.desafio_dio.entity.form.AvalicaoFisicaForm;
import com.example.desafio_dio.repository.AlunoRepository;
import com.example.desafio_dio.repository.AvaliacaoFisicaRepository;
import com.example.desafio_dio.service.AvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements AvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;


    @Override
    public AvaliacaoFisica create(AvalicaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        return null;
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return List.of();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
