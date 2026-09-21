package com.ebook.__livraria.service;

import com.ebook.__livraria.domain.Livro;
import com.ebook.__livraria.dto.LivroRequest;
import com.ebook.__livraria.repository.LivroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;


    @InjectMocks
    private LivroService livroService;

    @Test
    @DisplayName("Deve cadastrar um livro com sucesso quando o ISBN não existir no BD")
    void deveCadastrarlivroComSucesso() {
        //1.ARRAnge(Preparar)
        LivroRequest request = new LivroRequest(
                "Clean Code",
                "Pedro C. Martin",
                "132-1223123",
                new BigDecimal("120.33"),
                LocalDate.of(2008, 8,1)
        );

        // Diz ao Mockito: quando perguntar se o ISBN exise, responde FALSE
        when(livroRepository.existsByIsbn(request.getIsbn())).thenReturn(false);

        //Quando mandar salvar qualquer Livro
    }

}
