package com.example.desafio_dio.controller;

import com.example.desafio_dio.entity.AvaliacaoFisica;

import com.example.desafio_dio.entity.form.AvalicaoFisicaForm;
import com.example.desafio_dio.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvalicaoFisicaForm form) {
        return service.create(form);
    }


}
