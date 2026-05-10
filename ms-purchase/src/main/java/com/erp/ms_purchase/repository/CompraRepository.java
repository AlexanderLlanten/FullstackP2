package com.erp.ms_purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.ms_purchase.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
