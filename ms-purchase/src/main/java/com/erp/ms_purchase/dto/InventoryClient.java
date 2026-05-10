package com.erp.ms_purchase.dto;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-inventory")
public interface InventoryClient {

    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable Long id);

    @PutMapping("/api/productos/{id}/reducir-stock")
    void reducirStock(@PathVariable Long id, @RequestParam Integer cantidad);
}