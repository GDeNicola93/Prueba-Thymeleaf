package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.dtos.VentaListadoDto;
import com.example.prueba_thymeleaf.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer> {
    @Query("SELECT p from Producto p")
    Page<VentaListadoDto> getVentaListadoDto(Pageable pageable);
}
