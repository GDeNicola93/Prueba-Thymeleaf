package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.dtos.ProductoListaDto;
import com.example.prueba_thymeleaf.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
//    @Query("SELECT p.id as id,p.nombre as nombre,p.precio as precio from Producto p")
//    Page<ProductoListaDto> getProductosListaDto(Pageable pageable);
    
    @Query("SELECT p from Producto p")
    Page<ProductoListaDto> getProductosListaDto(Pageable pageable);
    
//    @Query("SELECT NEW com.example.prueba_thymeleaf.dtos.ProductoListaDto(p.id,p.nombre,p.precio) from Producto p")
//    Page<ProductoListaDto> getProductosListaDto(Pageable pageable);
    
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
    @Query("SELECT p from Producto p where p.id = ?1")
    Optional<ProductoListaDto> getProductoListaDtoByIdProducto(int id);

}
