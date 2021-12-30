package com.example.prueba_thymeleaf.dtos;

import java.time.LocalDateTime;


public interface VentaListadoDto {
    Integer getId();
    
    LocalDateTime getFechaHoraVenta();
    
    float getTotalVenta();
}
