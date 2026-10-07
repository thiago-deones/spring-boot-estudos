package com.lojavirtual.pedidos.controller;

import com.lojavirtual.pedidos.dtos.ProdutoRequest;
import com.lojavirtual.pedidos.dtos.ProdutoResponse;
import com.lojavirtual.pedidos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrar(
            @RequestBody @Valid ProdutoRequest request) {

        return produtoService.cadastrar(request);
    }

    @GetMapping
    public Page<ProdutoResponse> listar(Pageable pageable) {

        return produtoService.listar(pageable);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {

        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequest request) {

        return produtoService.atualizar(id, request);

    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        produtoService.deletar(id);
    }


}
