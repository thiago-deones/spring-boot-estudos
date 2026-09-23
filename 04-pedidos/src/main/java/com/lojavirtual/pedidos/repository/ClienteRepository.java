package com.lojavirtual.pedidos.repository;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.dtos.ClienteResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}