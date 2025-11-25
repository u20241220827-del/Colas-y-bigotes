package com.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.model.Producto;
import com.proyecto_final.repository.UsuarioRepository;
import com.proyecto_final.service.ProductoService;
import com.proyecto_final.service.VeterinarioService;

/**
 * Controlador encargado de administrar productos, veterinarios
 * y la vista principal del panel de administración.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VeterinarioService veterinarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Muestra el panel principal del administrador.
     *
     * @return nombre de la vista del panel de administración.
     */
    @GetMapping("")
    public String panelAdmin() {
        return "admin_vista";
    }

    /**
     * Muestra el formulario para registrar un nuevo producto.
     *
     * @param model modelo utilizado para enviar datos a la vista.
     * @return vista del formulario de producto.
     */
    @GetMapping("/productos/nuevo")
    public String formularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos_form";
    }

    /**
     * Guarda un nuevo producto en el sistema.
     *
     * @param producto instancia enviada desde el formulario.
     * @return redirección a la lista de productos.
     */
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.registrarProducto(producto);
        return "redirect:/admin/productos";
    }

    /**
     * Lista todos los productos registrados.
     *
     * @param model modelo utilizado para enviar la lista a la vista.
     * @return vista de listado de productos.
     */
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos_lista";
    }

    /**
     * Muestra el formulario para crear un nuevo veterinario.
     * Incluye la lista de usuarios disponibles.
     *
     * @param model modelo utilizado para enviar datos a la vista.
     * @return vista del formulario de veterinario.
     */
    @GetMapping("/veterinarios/nuevo")
    public String formularioNuevoVeterinario(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "veterinario_form";
    }

    /**
     * Registra un veterinario asignando un usuario existente.
     *
     * @param usuarioId ID del usuario seleccionado.
     * @param nombre nombre completo del veterinario.
     * @param especialidad especialidad ingresada.
     * @return redirección a la lista de veterinarios.
     */
    @PostMapping("/veterinarios/guardar")
    public String guardarVeterinario(
            @RequestParam Long usuarioId,
            @RequestParam String nombre,
            @RequestParam String especialidad) {

        veterinarioService.ascenderAVeterinario(usuarioId, nombre, especialidad);
        return "redirect:/admin/veterinarios";
    }

    /**
     * Lista todos los veterinarios registrados en el sistema.
     *
     * @param model modelo utilizado para enviar la lista a la vista.
     * @return vista de listado de veterinarios.
     */
    @GetMapping("/veterinarios")
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        return "veterinario_lista";
    }
}
