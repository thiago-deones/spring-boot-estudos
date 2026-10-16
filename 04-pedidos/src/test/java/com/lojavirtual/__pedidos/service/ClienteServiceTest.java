package com.lojavirtual.__pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.dtos.ClienteRequest;
import com.lojavirtual.pedidos.dtos.ClienteResponse;
import com.lojavirtual.pedidos.exception.RegraDeNegocioException;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import com.lojavirtual.pedidos.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveCadastrarClienteComSucesso() {

        //ARRANGE

        ClienteRequest request = new ClienteRequest(
                "João",
                "joao@email.com",
                "12345678900"
        );

        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNome("João");
        cliente.setEmail("joao@email.com");
        cliente.setCpf("12345678900");

        when(clienteRepository.existsByCpf(request.cpf()))
                .thenReturn(false);

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        //ACT

        ClienteResponse response = clienteService.cadastrar(request);

        //ASSERT

        assertEquals(1L, response.id());
        assertEquals("João", response.nome());
        assertEquals("joao@email.com", response.email());

        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deveImpedirCadastroComCpfDuplicado() {

        //ARRANGE

        ClienteRequest request = new ClienteRequest(
                "João",
                "joao@email.com",
                "12345678900"
        );

        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNome("joão");
        cliente.setEmail("joao@email.com");
        cliente.setCpf("12345678900");

        when(clienteRepository.existsByCpf(request.cpf()))
                .thenReturn(true);

        // ACT + ASSERT

        assertThrows(
                RegraDeNegocioException.class,
                () -> clienteService.cadastrar(request)
        );
    }

    @Test
    void deveimpedorCadastroComCpfDuplicado() {

        //ARRANGE

        ClienteRequest request = new ClienteRequest(
                "João",
                "joao@email.com",
                "12345678900"
        );

        when(clienteRepository.existsByCpf(request.cpf()))
                .thenReturn(true);

        // ACT + ASSERT

        assertThrows(
                RegraDeNegocioException.class,
                () -> clienteService.cadastrar(request)
        );
    }
}
