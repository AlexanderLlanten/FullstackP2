package com.erp.ms_purchase.controller;
import com.erp.ms_purchase.dto.CompraRequestDTO;
import com.erp.ms_purchase.model.Compra;
import com.erp.ms_purchase.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<String> realizarCompra(@RequestBody CompraRequestDTO request) {
        compraService.registrarCompra(request);
        return ResponseEntity.ok("Compra registrada con éxito y stock actualizado.");
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        List<Compra> compras = compraService.obtenerTodas();
        return ResponseEntity.ok(compras);
    }
}