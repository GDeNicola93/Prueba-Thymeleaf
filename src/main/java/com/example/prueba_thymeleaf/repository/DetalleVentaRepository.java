package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Integer> {
    
}
