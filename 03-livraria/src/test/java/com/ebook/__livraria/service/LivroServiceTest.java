package com.ebook.__livraria.service;

import com.ebook.__livraria.domain.Livro;
import com.ebook.__livraria.dto.LivroRequest;
import com.ebook.__livraria.dto.LivroResponse;
import com.ebook.__livraria.exception.RecursoNaoEncontradoException;
import com.ebook.__livraria.exception.RegraDeNegocioException;
import com.ebook.__livraria.repository.LivroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;


    @InjectMocks
    private LivroService livroService;

    @Test
    @DisplayName("Deve cadastrar um livro com sucesso quando o ISBN não existir no BD")
    void deveCadastrarlivroComSucesso() {
// 1. ARRANGE
        LivroRequest request = new LivroRequest("Clean Code", "Robert C. Martin", "978-0132350884", new BigDecimal("120.00"), LocalDate.of(2008, 8, 1));

        when(livroRepository.existsByIsbn(request.getIsbn())).thenReturn(false);

        // Configura o mock para responder simulando o salvamento e gerando um ID
        when(livroRepository.save(any(Livro.class))).thenAnswer(invocation -> {
            Livro livroEnviado = invocation.getArgument(0);
            livroEnviado.setId(1L); // Simula o ID gerado pelo banco de dados
            return livroEnviado;
        });

        // 2. ACT
        LivroResponse response = livroService.cadastrar(request);

        // 3. ASSERT
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Clean Code", response.titulo());
        assertEquals("Robert C. Martin", response.autor());
        assertEquals("978-0132350884", response.isbn());
        assertEquals(new BigDecimal("120.00"), response.preco());

        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    @DisplayName("Deve lançar RegraDeNegocioException quando o ISBN já existir no banco")
    void deveLancarExcecaoQuandoIsbnJaExistir() {
        // ARRANGE
        LivroRequest request = new LivroRequest("Clean Code", "Robert C. Martin", "978-0132350884", new BigDecimal("120.00"), LocalDate.of(2008, 8, 1));

        //Diz ao Mockito: qunado perguntar se o ISBN existe, responda TRUE
        when(livroRepository.existsByIsbn(request.getIsbn())).thenReturn(true);

        // ACT e ASSERT
        //Verificar se ao chamar o cadastrar, lança a exceção RegraDeNegocioExceptiom
        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () -> livroService.cadastrar(request));

        assertEquals("Já existe um livro cadastrado com o ISBN: " + request.getIsbn(), exception.getMessage());

        // Garante que o save() NUNCA foi chamado (pois travou na validação antes)
        verify(livroRepository, never()).save(any(Livro.class));
    }

    @Test
    @DisplayName("Deve retornar LivroResponse ao buscar por ID existente")
    void deveRetornarLivroResponseAoBuscarPorIdEExistente() {
        //Arrange
        Long idExistente = 1L;
        Livro livro = new Livro();
        livro.setId(idExistente);
        livro.setTitulo("Clean Code");
        livro.setAutor("Robert C. Martin");
        livro.setIsbn("978-0132350884");
        livro.setPreco(new BigDecimal("120.00"));
        livro.setDataPublicacao(LocalDate.of(2008, 8, 1));

        //Ensina o mock a retornar Optional.of(livro) quando buscar por ID
        when(livroRepository.findById(idExistente)).thenReturn(Optional.of(livro));

        // 2. ACT
        LivroResponse response = livroService.buscarPorid(idExistente);

        // 3. ASSERT
        assertNotNull(response);
        assertEquals(idExistente, response.id());
        assertEquals("Clean Code", response.titulo());

        // Garante que o findById foi chamado exatamente 1 vez com o ID 1L
        verify(livroRepository, times(1)).findById(idExistente);
    }

    @Test
    @DisplayName("Deve lançar RecursoNaoEncontradoException quando buscar por ID inexistente")
    void deveLancarExceptionAoBuscarPorIdInexistente() {
        Long idInexistente = 99L;

        when(livroRepository.findById(idInexistente)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> livroService.buscarPorid((idInexistente))
        );

        assertEquals("Livro não encontrado pelo Id: " + idInexistente, exception.getMessage());
        verify(livroRepository, times(1)).findById(idInexistente);
    }
}
