package com.example.prueba_thymeleaf.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    private String descripcion;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria")
    private List<SubCategoria> subCategorias;
}
