package com.lojavirtual.pedidos.service;

import com.lojavirtual.pedidos.domain.Produto;
import com.lojavirtual.pedidos.dtos.ProdutoRequest;
import com.lojavirtual.pedidos.dtos.ProdutoResponse;
import com.lojavirtual.pedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponse cadastrar(ProdutoRequest request) {
        if (produtoRepository.existsByNome(request.nome())) {
            throw new RuntimeException(
                    "Já existe um produto com este nome: " + request.nome()
            );
        }

        Produto produto = new Produto();
        request.preencher(produto);
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoResponse.fromEntity(produtoSalvo);
    }

    public Page<ProdutoResponse> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(ProdutoResponse::fromEntity);
    }

    public ProdutoResponse buscar(Long id) {
        Produto produto = buscarEntidadeById(id);
        return ProdutoResponse.fromEntity(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        if (produtoRepository.existyByNomeAndIdNot(request.nome(), id)) {
            throw new RuntimeException(
                    "Já existe outro produto com este nome " + request.nome()
            );
        }
        Produto produto = buscarEntidadeById(id);
        request.preencher(produto);
        Produto produtoAtualizado = produtoRepository.save(produto);
        return ProdutoResponse.fromEntity(produtoAtualizado);
    }

    public void deletar(Long id) {
        Produto produto = buscarEntidadeById(id);
        produtoRepository.delete(produto);
    }

    private Produto buscarEntidadeById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe este ID: " + id));
    }
}
