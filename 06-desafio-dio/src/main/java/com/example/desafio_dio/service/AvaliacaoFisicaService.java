package com.example.desafio_dio.service;

import com.example.desafio_dio.entity.AvaliacaoFisica;
import com.example.desafio_dio.entity.form.AvaliacaoUpdateForm;
import com.example.desafio_dio.entity.form.AvalicaoFisicaForm;

import java.util.List;

public interface AvaliacaoFisicaService {

    AvaliacaoFisica create(AvalicaoFisicaForm form);

    AvaliacaoFisica get(Long id);

    List<AvaliacaoFisica> getAll();

    AvaliacaoFisica update(Long id, AvaliacaoUpdateForm formUpdate);

    void delete(Long id);
}
