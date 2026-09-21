package com.ebook.__livraria.controller;

import com.ebook.__livraria.dto.LivroRequest;
import com.ebook.__livraria.dto.LivroResponse;
import com.ebook.__livraria.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse cadastrar(@RequestBody @Valid LivroRequest livroRequest) {
        return livroService.cadastrar(livroRequest);
    }

    @GetMapping
    public Page<LivroResponse> listar(Pageable pageable) {
        return livroService.listar(pageable);
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorid(id);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequest livroRequest) {
        return livroService.atualizar(id, livroRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        livroService.excluir(id);
    }


}
