
package com.example.prueba_thymeleaf.controller;

import com.example.prueba_thymeleaf.entity.DetalleVenta;
import com.example.prueba_thymeleaf.entity.Venta;
import com.example.prueba_thymeleaf.repository.VentaRepository;
import com.example.prueba_thymeleaf.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    VentaService ventaServicio;
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String list(){
        return "venta/lista";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ver/{id}")
    public String verVenta(@PathVariable("id") int id,Model model){
        if(!ventaServicio.getOne(id).isPresent()){
            return "venta/lista";
        }
        model.addAttribute("venta",ventaServicio.getOne(id).get());
        return "venta/ver";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nueva")
    public String nueva(Model model){
        model.addAttribute("ventaForm",new Venta());
        return "venta/nueva";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(Model model,@ModelAttribute("ventaForm") Venta ventaForm){
        ventaServicio.guardar(ventaForm);
        return "venta/lista";
    }
}
