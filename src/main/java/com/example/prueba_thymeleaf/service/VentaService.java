package com.example.prueba_thymeleaf.service;

import com.example.prueba_thymeleaf.dtos.VentaListadoDto;
import com.example.prueba_thymeleaf.entity.DetalleVenta;
import com.example.prueba_thymeleaf.entity.Venta;
import com.example.prueba_thymeleaf.repository.DetalleVentaRepository;
import com.example.prueba_thymeleaf.repository.VentaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;
    
    @Autowired
    DetalleVentaRepository detalleVentaRepository;
    
    public Page<VentaListadoDto> list(Pageable pageable){
        return ventaRepository.getVentaListadoDto(pageable);
    }
    
     public Optional<Venta> getOne(int id){
        return ventaRepository.findById(id);
    }
    
    public void guardar(Venta v){
        //Primero guardo la venta
        ventaRepository.save(v);
        
        //Despues guardo los detalles ya que estos ultimos tienen el puntero a la venta por lo tanto necesito
        //tener la venta primero guardada.
        
        for(DetalleVenta dv : v.getDetallesDeVenta()){
            dv.setVenta(v);
            dv.setPrecio(dv.getProducto().getPrecio());
            detalleVentaRepository.save(dv);
        }
    }
}
