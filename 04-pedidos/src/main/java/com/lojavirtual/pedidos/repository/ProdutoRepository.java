package com.lojavirtual.pedidos.repository;

import com.lojavirtual.pedidos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);

    boolean existyByNomeAndIdNot(String nome, Long id);
}
