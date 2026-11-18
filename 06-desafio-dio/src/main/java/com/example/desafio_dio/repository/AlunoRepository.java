package com.example.desafio_dio.repository;

import com.example.desafio_dio.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
