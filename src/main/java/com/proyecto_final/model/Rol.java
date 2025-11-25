package com.proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa un rol dentro del sistema.
 * Los roles comunes incluyen: USER, ADMIN y VET.
 */
@Entity
public class Rol {

    /**
     * Identificador único del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del rol. Ejemplos: USER, ADMIN, VET.
     */
    private String nombre;

    /**
     * Obtiene el identificador del rol.
     *
     * @return id del rol
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del rol.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol.
     *
     * @return nombre del rol
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del rol.
     *
     * @param nombre nuevo nombre del rol
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
