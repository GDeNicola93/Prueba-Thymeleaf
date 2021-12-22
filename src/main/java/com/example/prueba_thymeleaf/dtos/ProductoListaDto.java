package com.example.prueba_thymeleaf.dtos;

import lombok.Data;

//Dto basado en clase
//@Data
//public class ProductoListaDto{
//    Integer id;
//    
//    String nombre;
//    
//    float precio;
//
//    public ProductoListaDto(Integer id, String nombre, float precio) {
//        this.id = id;
//        this.nombre = nombre;
//        this.precio = precio;
//    }
//    
//    public String getInfoCompleta(){
//        return this.nombre + " - Precio: " + this.precio;
//    }
//}

//Dto basado en interface
public interface ProductoListaDto {
    Integer getId();
    
    String getNombre();
    
    float getPrecio();
    
    String getInfoCompleta();
}
