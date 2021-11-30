package com.example.prueba_thymeleaf.controller;

import com.example.prueba_thymeleaf.dtos.ProductoListaDto;
import com.example.prueba_thymeleaf.entity.Producto;
import com.example.prueba_thymeleaf.entity.Usuario;
import com.example.prueba_thymeleaf.security.UsuarioPrincipal;
import com.example.prueba_thymeleaf.service.CategoriaService;
import com.example.prueba_thymeleaf.service.ProductoService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
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
    public String list(HttpServletRequest request,Model model){
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 8; //default page size is 10
        
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
        //model.addAttribute("productos",productoService.list());
        model.addAttribute("productos",productoService.list(PageRequest.of(page, size)));
        return "producto/lista";
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
    public String detalle(@PathVariable("id") int id,Model model){
        if(!productoService.existsById(id)){
            return "redirect:/producto/lista";
        }
        model.addAttribute("producto",productoService.getOne(id).get());
        return "/producto/detalle";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id,Model model){
        if(!productoService.existsById(id)){
            return "redirect:/producto/lista";
        }
        model.addAttribute("productoForm",productoService.getOne(id).get());
        model.addAttribute("categorias",categoriaService.list());
        return "producto/editar";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("productoForm") Producto productoForm,BindingResult bindingResult,Authentication authentication,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias",categoriaService.list());
            return "producto/editar";
        }
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) authentication.getPrincipal();
        Producto producto = productoService.getOne(productoForm.getId()).get();
        producto.setNombre(productoForm.getNombre());
        producto.setPrecio(productoForm.getPrecio());
        producto.setCategoria(productoForm.getCategoria());
        producto.setUsuarioUltimaActualizacion(new Usuario(userLogeado.getId()));
        productoService.save(producto);
        return "redirect:/producto/lista";
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id")int id){
        if(productoService.existsById(id)){
            productoService.delete(id);
            return "redirect:/producto/lista";
        }
        return null;
    }
}
