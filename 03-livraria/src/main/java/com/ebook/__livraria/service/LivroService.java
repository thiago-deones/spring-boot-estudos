package com.ebook.__livraria.service;

import com.ebook.__livraria.domain.Livro;
import com.ebook.__livraria.dto.LivroRequest;
import com.ebook.__livraria.dto.LivroResponse;
import com.ebook.__livraria.exception.RecursoNaoEncontradoException;
import com.ebook.__livraria.exception.RegraDeNegocioException;
import com.ebook.__livraria.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroResponse cadastrar(LivroRequest request) {
        if (livroRepository.existsByIsbn(request.getIsbn())) {
            throw new RegraDeNegocioException("Já existe um livro cadastrado com o ISBN: " + request.getIsbn());
        }

        Livro livro = new Livro();

        request.preencher(livro);

        Livro livroSalvo = livroRepository.save(livro);
        return LivroResponse.fromEntity(livroSalvo);
    }

    public Page<LivroResponse> listar(Pageable pageable) {
        return livroRepository.findAll(pageable)
                .map(LivroResponse::fromEntity);
    }

    public LivroResponse buscarPorid(Long id) {
        Livro livro = buscarEntidadePorId(id);
        return LivroResponse.fromEntity(livro);
    }


    public LivroResponse atualizar(Long id, LivroRequest request) {
        Livro livro = buscarEntidadePorId(id);
        request.preencher(livro);
        Livro livroAtualizado = livroRepository.save(livro);
        return LivroResponse.fromEntity(livroAtualizado);
    }

    public void excluir(Long id) {
        Livro livro = buscarEntidadePorId(id);
        livroRepository.delete(livro);
    }


    private Livro buscarEntidadePorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado pelo Id: " + id));
    }
}
