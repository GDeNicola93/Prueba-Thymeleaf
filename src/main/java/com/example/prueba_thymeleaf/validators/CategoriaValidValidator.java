package com.example.prueba_thymeleaf.validators;

import com.example.prueba_thymeleaf.entity.Categoria;
import com.example.prueba_thymeleaf.service.CategoriaService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoriaValidValidator implements ConstraintValidator<CategoriaValid,Categoria> {
    @Autowired
    private CategoriaService categoriaService;
    
    @Override
    public boolean isValid(Categoria cat, ConstraintValidatorContext context) {
        if(categoriaService.findByCategoria(cat).isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
