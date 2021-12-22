package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer> {
    
}
