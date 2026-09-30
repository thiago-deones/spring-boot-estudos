package com.lojavirtual.pedidos.controller;

import com.lojavirtual.pedidos.dtos.PedidoRequest;
import com.lojavirtual.pedidos.dtos.PedidoResponse;
import com.lojavirtual.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse cadastrar(@RequestBody @Valid PedidoRequest request) {
        return pedidoService.cadastrar(request);
    }
}
