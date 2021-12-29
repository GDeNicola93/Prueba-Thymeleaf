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
//    String categoriaNombre;
//
//    public ProductoListaDto(Integer id, String nombre, float precio, String categoriaNombre) {
//        this.id = id;
//        this.nombre = nombre;
//        this.precio = precio;
//        this.categoriaNombre = categoriaNombre;
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
