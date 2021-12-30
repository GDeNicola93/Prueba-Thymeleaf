package com.example.prueba_thymeleaf.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombreVenta;
    
    @CreatedDate
    private LocalDateTime fechaHoraVenta;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "venta")
    private List<DetalleVenta> detallesDeVenta;
    
    public float getTotalVenta(){
        float sumaTotal = 0;
        for(DetalleVenta dv : this.getDetallesDeVenta()){
            sumaTotal = sumaTotal + dv.getPrecio();
        }
        return sumaTotal;
    }
}
