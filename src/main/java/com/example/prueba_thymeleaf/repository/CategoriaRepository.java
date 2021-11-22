package com.example.prueba_thymeleaf.repository;

import com.example.prueba_thymeleaf.entity.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{
    
    @Query("SELECT c FROM Categoria c where c = ?1")
    Optional<Categoria> findByCategoria(Categoria cat);
}
