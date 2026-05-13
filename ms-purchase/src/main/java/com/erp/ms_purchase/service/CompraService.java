package com.erp.ms_purchase.service;
import com.erp.ms_purchase.client.InventarioClient;
import com.erp.ms_purchase.dto.CompraRequestDTO;
import com.erp.ms_purchase.model.Compra;
import com.erp.ms_purchase.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService { 

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private InventarioClient inventarioClient;

    @Transactional
    public void registrarCompra(CompraRequestDTO compraDTO) {
        Compra compra = new Compra();
        compra.setProveedorId(compraDTO.getProveedorId());
        compra.setFecha(LocalDateTime.now());
        compraRepository.save(compra);

        compraDTO.getItems().forEach(item -> {
            inventarioClient.aumentarStock(item.getProductoId(), item.getCantidad());
        });
    }

    public List<Compra> obtenerTodas() {
        return compraRepository.findAll();
    }
}