package com.example.prueba_thymeleaf.entity;

import com.example.prueba_thymeleaf.validators.CategoriaValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
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
    
    @CreatedDate
    private LocalDateTime fechaHoraCreacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @CategoriaValid
    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategoria subCategoria;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuarioRegistro;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuarioUltimaActualizacion;

    public Producto() {
    }

    public Producto(String nombre, float precio, Usuario usuarioRegistro) {
        this.nombre = nombre;
        this.precio = precio;
        this.usuarioRegistro = usuarioRegistro;
    }
    
    public String getInfoCompleta(){
        //return this.nombre + " Precio: " + this.precio + " (" + this.categoria.getNombre() + ")";
        return this.nombre + " Precio: " + this.precio;
    }
}
