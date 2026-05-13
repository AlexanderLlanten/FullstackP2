package com.erp.ms_purchase.dto;
import lombok.Data;
import java.util.List;

@Data
public class CompraRequestDTO {
    private Long proveedorId;
    private List<CompraItemDTO> items;
}