package com.example.prueba_thymeleaf.service;

import com.example.prueba_thymeleaf.entity.Categoria;
import com.example.prueba_thymeleaf.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }
}