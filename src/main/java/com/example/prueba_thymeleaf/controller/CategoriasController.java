package com.example.prueba_thymeleaf.controller;

import com.example.prueba_thymeleaf.entity.SubCategoria;
import com.example.prueba_thymeleaf.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping(path = "/{id_categoria}/sub_categorias",produces = "application/json")
    public @ResponseBody List<SubCategoria> getSubCategoriasByIdCategoria(@PathVariable Integer id_categoria){
        return categoriaService.getSubCategoriasByIdCategoria(id_categoria);
    }
    
}
