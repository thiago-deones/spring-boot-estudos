package com.lojavirtual.pedidos.service;

import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.dtos.ClienteRequest;
import com.lojavirtual.pedidos.dtos.ClienteResponse;
import com.lojavirtual.pedidos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse cadastrar(ClienteRequest request) {

        if (clienteRepository.existsByCpf(request.cpf())) {
            throw new RuntimeException(
                    "Já existe um cliente cadastrado com este CPF: " + request.cpf()
            );
        }
        Cliente cliente = new Cliente();

        request.preencher(cliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(clienteSalvo);
    }

    public Page<ClienteResponse> listar(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(ClienteResponse::fromEntity);
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        return ClienteResponse.fromEntity(cliente);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        if (clienteRepository.existsByCpfAndIdNot(request.cpf(), id)) {
            throw new RuntimeException(
                    "Já existe outro cliente cadastrado com este CPF: " + request.cpf()
            );
        }
        Cliente cliente = buscarEntidadePorId(id);
        request.preencher(cliente);
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(clienteAtualizado);
    }

    public void deletar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        clienteRepository.delete(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Cliente não encontrado com o ID " + id));
    }
}
