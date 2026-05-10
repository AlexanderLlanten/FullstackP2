package com.erp.ms_purchase.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.erp.ms_purchase.dto.ClientsClient;
import com.erp.ms_purchase.dto.InventoryClient;
import com.erp.ms_purchase.model.Compra;
import com.erp.ms_purchase.model.DetalleCompra;
import com.erp.ms_purchase.repository.CompraRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final InventoryClient inventoryClient;
    private final ClientsClient clientsClient;

    @Transactional
    public Compra crearCompra(Compra compra) {
        // 1. Validar cliente
        clientsClient.obtenerClientePorId(compra.getClientId());

        // 2. Validar stock y calcular total
        double total = 0.0;
        for (DetalleCompra item : compra.getItems()) {
            // Feign devuelve ProductoDTO (debes definirlo)
            var producto = inventoryClient.obtenerProductoPorId(item.getProductId());
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para producto " + item.getProductId());
            }
            item.setPrecioUnitario(producto.getPrecio());
            item.setCompra(compra);
            total += item.getCantidad() * producto.getPrecio();

            // 3. Actualizar stock (suponiendo que el endpoint existe)
            inventoryClient.reducirStock(item.getProductId(), item.getCantidad());
        }

        compra.setFecha(LocalDateTime.now());
        compra.setTotal(total);
        return compraRepository.save(compra);
    }
}