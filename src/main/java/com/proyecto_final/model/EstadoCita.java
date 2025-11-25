package com.proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa el estado de una cita veterinaria.
 * Los estados comunes pueden incluir: PROGRAMADA, EN_CURSO, TERMINADA.
 */
@Entity
public class EstadoCita {

    /**
     * Identificador único del estado de la cita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del estado. Ejemplos: PROGRAMADA, EN_CURSO, TERMINADA.
     */
    private String nombre;

    /**
     * Obtiene el identificador del estado de la cita.
     *
     * @return id del estado
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del estado de la cita.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del estado.
     *
     * @return nombre del estado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del estado de la cita.
     *
     * @param nombre nuevo nombre del estado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
