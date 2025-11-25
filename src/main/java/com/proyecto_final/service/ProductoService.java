package com.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Producto;
import com.proyecto_final.repository.ProductoRepository;

/**
 * Servicio encargado de la gestión de productos, incluyendo registro,
 * consulta, actualización de stock y búsqueda por identificador.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Registra un nuevo producto en el sistema.
     *
     * @param producto Producto a registrar.
     * @return Producto almacenado en la base de datos.
     */
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Obtiene la lista completa de productos registrados.
     *
     * @return Lista de productos existentes.
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    /**
     * Actualiza el stock de un producto utilizando su ID.
     *
     * @param productoId ID del producto a actualizar.
     * @param nuevoStock Nuevo valor de stock.
     * @return Producto actualizado.
     * @throws RuntimeException si el producto no existe.
     */
    public Producto actualizarStock(Long productoId, int nuevoStock) {

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(nuevoStock);

        return productoRepository.save(producto);
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id ID del producto.
     * @return Producto encontrado.
     * @throws RuntimeException si el producto no existe.
     */
    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
