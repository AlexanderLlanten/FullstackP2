package com.erp.ms_purchase.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-inventory", url = "http://localhost:8081/api/productos")
public interface InventarioClient {

    @PutMapping("/{id}/stock-aumentar")
    void aumentarStock(@PathVariable("id") Long id, @RequestParam("cantidad") Integer cantidad);
}