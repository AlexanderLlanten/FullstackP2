package com.erp.ms_purchase.controller;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.ms_purchase.model.Compra;
import com.erp.ms_purchase.service.CompraService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> crearCompra(@Valid @RequestBody Compra compra) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(compraService.crearCompra(compra));
    }
}