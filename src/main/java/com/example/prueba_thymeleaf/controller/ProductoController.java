package com.example.prueba_thymeleaf.controller;

import com.example.prueba_thymeleaf.dtos.ProductoListaDto;
import com.example.prueba_thymeleaf.entity.Producto;
import com.example.prueba_thymeleaf.entity.Usuario;
import com.example.prueba_thymeleaf.security.UsuarioPrincipal;
import com.example.prueba_thymeleaf.service.CategoriaService;
import com.example.prueba_thymeleaf.service.ProductoService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    
    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/producto/lista");
        List<ProductoListaDto> list = productoService.list();
        mv.addObject("productos",list);
        return mv;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("productoForm",new Producto());
        model.addAttribute("categorias",categoriaService.list());
        return "producto/nuevo";
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("productoForm") Producto productoForm,BindingResult bindingResult,Authentication authentication,Model model){
        if (bindingResult.hasErrors()) {
            //model.addAttribute("productoForm",productoForm);
            model.addAttribute("categorias",categoriaService.list());
            return "producto/nuevo";
        }
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) authentication.getPrincipal();      
        productoForm.setUsuarioRegistro(new Usuario(userLogeado.getId()));
        productoForm.setUsuarioRegistro(new Usuario(userLogeado.getId()));
        productoService.save(productoForm);
        return "redirect:/producto/lista";
    }
    
    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ModelAndView("redirect:/producto/lista");
        Producto producto = productoService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/producto/detalle");
        mv.addObject("producto", producto);
        return mv;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ModelAndView("redirect:/producto/lista");
        Producto producto = productoService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/producto/editar");
        mv.addObject("producto", producto);
        return mv;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam int id, @RequestParam String nombre, @RequestParam float precio,Authentication authentication){
        if(!productoService.existsById(id))
            return new ModelAndView("redirect:/producto/lista");
        ModelAndView mv = new ModelAndView();
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) authentication.getPrincipal();
        Producto producto = productoService.getOne(id).get();
        if(StringUtils.isBlank(nombre)){
            mv.setViewName("producto/editar");
            mv.addObject("producto", producto);
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if(precio <1 ){
            mv.setViewName("producto/editar");
            mv.addObject("error", "el precio debe ser mayor que cero");
            mv.addObject("producto", producto);
            return mv;
        }
        if(productoService.existsByNombre(nombre) && productoService.getByNombre(nombre).get().getId() != id){
            mv.setViewName("producto/editar");
            mv.addObject("error", "ese nombre ya existe");
            mv.addObject("producto", producto);
            return mv;
        }

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setUsuarioUltimaActualizacion(new Usuario(userLogeado.getId()));
        productoService.save(producto);
        return new ModelAndView("redirect:/producto/lista");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(productoService.existsById(id)){
            productoService.delete(id);
            return new ModelAndView("redirect:/producto/lista");
        }
        return null;
    }
}
