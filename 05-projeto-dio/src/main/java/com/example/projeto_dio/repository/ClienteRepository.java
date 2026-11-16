package com.example.projeto_dio.repository;

import com.example.projeto_dio.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
