package com.lojavirtual.pedidos.controller;


import com.lojavirtual.pedidos.domain.Cliente;
import com.lojavirtual.pedidos.dtos.ClienteRequest;
import com.lojavirtual.pedidos.dtos.ClienteResponse;
import com.lojavirtual.pedidos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse cadastrar(@RequestBody @Valid ClienteRequest request) {
        return clienteService.cadastrar(request);
    }

    @GetMapping
    public Page<ClienteResponse> listar(Pageable pageable) {
        return clienteService.listar(pageable);
    }

    @GetMapping("{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteRequest request) {

        return clienteService.atualizar(id,request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        clienteService.deletar(id);
    }

}
