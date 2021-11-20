package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.dtos.ProductoListaDto;
import com.example.prueba_thymeleaf.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p from Producto p")
    List<ProductoListaDto> getProductosListaDto();
    
    
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}
