package com.example.prueba_thymeleaf.service;

import com.example.prueba_thymeleaf.entity.Categoria;
import com.example.prueba_thymeleaf.entity.SubCategoria;
import com.example.prueba_thymeleaf.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> findByCategoria(Categoria c){
        return categoriaRepository.findByCategoria(c);
    }
    
    public List<SubCategoria> getSubCategoriasByIdCategoria(int idCategoria){
        return categoriaRepository.findById(idCategoria).get().getSubCategorias();
    }
}
