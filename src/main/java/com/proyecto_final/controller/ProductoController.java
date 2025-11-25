package com.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.model.Producto;
import com.proyecto_final.service.ProductoService;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los productos.
 * Permite registrar nuevos productos, listar los existentes y actualizar su stock.
 * 
 * <p>Las vistas asociadas a este controlador se encuentran en el directorio
 * <code>templates/productos/</code>.</p>
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Muestra el formulario para registrar un nuevo producto.
     *
     * @param model Modelo utilizado para enviar datos a la vista.
     * @return Nombre de la plantilla que contiene el formulario de registro.
     */
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/registrar";
    }

    /**
     * Procesa el registro de un nuevo producto en el sistema.
     *
     * @param producto Objeto {@link Producto} recibido desde el formulario.
     * @return Redirección hacia la lista de productos registrados.
     */
    @PostMapping("/registrar")
    public String registrarProducto(@ModelAttribute Producto producto) {
        productoService.registrarProducto(producto);
        return "redirect:/productos/lista";
    }

    /**
     * Lista todos los productos registrados en el sistema.
     *
     * @param model Modelo utilizado para enviar la lista de productos a la vista.
     * @return Nombre de la plantilla que muestra la lista de productos.
     */
    @GetMapping("/lista")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "productos/lista";
    }

    /**
     * Muestra el formulario para actualizar el stock de un producto.
     *
     * @param id     Identificador del producto cuyo stock se actualizará.
     * @param model  Modelo utilizado para enviar los datos del producto a la vista.
     * @return Nombre de la plantilla que contiene el formulario de actualización de stock.
     */
    @GetMapping("/{id}/stock")
    public String mostrarFormularioStock(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProducto(id);
        model.addAttribute("producto", producto);
        return "productos/stock";
    }

    /**
     * Procesa la actualización del stock de un producto.
     *
     * @param productoId Identificador del producto.
     * @param stock      Nuevo valor de stock a asignar.
     * @return Redirección hacia la lista de productos.
     */
    @PostMapping("/actualizar-stock")
    public String actualizarStock(
            @RequestParam Long productoId,
            @RequestParam int stock) {

        productoService.actualizarStock(productoId, stock);
        return "redirect:/productos/lista";
    }
}
