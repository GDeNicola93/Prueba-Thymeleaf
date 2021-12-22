
package com.example.prueba_thymeleaf.controller;

import com.example.prueba_thymeleaf.entity.Venta;
import com.example.prueba_thymeleaf.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    VentaRepository repository;
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String list(){
        return "venta/lista";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nueva")
    public String nueva(Model model){
        model.addAttribute("ventaForm",new Venta());
        return "venta/nueva";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@ModelAttribute("ventaForm") Venta ventaForm){
        System.out.println(ventaForm.getDetallesDeVenta());
        //repository.save(ventaForm);
        return "venta/lista";
    }
}
