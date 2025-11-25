package com.proyecto_final.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa un producto disponible en la clínica veterinaria.
 * Puede corresponder a medicamentos, insumos o cualquier artículo
 * que pueda ser prescrito o vendido.
 */
@Entity
public class Producto {

    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción detallada del producto.
     */
    private String descripcion;

    /**
     * Precio del producto.
     */
    private BigDecimal precio;

    /**
     * Cantidad disponible en inventario.
     */
    private Integer stock;

    /**
     * Obtiene el identificador del producto.
     *
     * @return id del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio como BigDecimal
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible en inventario.
     *
     * @return stock actual
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del producto.
     *
     * @param stock nuevo stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
