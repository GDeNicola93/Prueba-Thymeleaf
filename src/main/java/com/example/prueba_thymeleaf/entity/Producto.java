package com.example.prueba_thymeleaf.entity;

import com.example.prueba_thymeleaf.validators.CategoriaValid;
import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import lombok.Data;

@Entity
@Data
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(unique = true)
    @NotBlank(message = "El nombre del producto es requerido.")
    private String nombre;
    
    @Min(value = 1, message= "El precio debe ser mayor a 1")
    private float precio;
    
    @ManyToOne
    @CategoriaValid
    private Categoria categoria;
    
    @ManyToOne
    private SubCategoria subCategoria;
    
    @NotNull
    @OneToOne
    private Usuario usuarioRegistro;
    
    @OneToOne
    private Usuario usuarioUltimaActualizacion;

    public Producto() {
    }

    public Producto(String nombre, float precio, Usuario usuarioRegistro) {
        this.nombre = nombre;
        this.precio = precio;
        this.usuarioRegistro = usuarioRegistro;
    }
}
