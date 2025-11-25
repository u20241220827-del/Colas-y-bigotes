package com.proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

/**
 * Representa a un veterinario dentro del sistema.
 * Cada veterinario está asociado a un usuario del sistema mediante una
 * relación uno a uno, que define la cuenta utilizada para iniciar sesión.
 *
 * La entidad almacena información básica como el nombre completo
 * y la especialidad profesional del veterinario.
 */
@Entity
public class Veterinario {

    /**
     * Identificador único del veterinario.
     * Se genera automáticamente mediante estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del veterinario.
     */
    private String nombreCompleto;

    /**
     * Especialidad clínica o área de enfoque del veterinario.
     */
    private String especialidad;

    /**
     * Relación uno a uno con la entidad Usuario.
     * Representa la cuenta del veterinario en el sistema.
     * La columna 'usuario_id' en la tabla 'veterinario' almacena esta referencia.
     */
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cuenta;

    // -----------------------------------------------------
    // Getters y Setters
    // -----------------------------------------------------

    /**
     * Obtiene el identificador único del veterinario.
     *
     * @return id del veterinario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del veterinario.
     *
     * @param id identificador del veterinario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del veterinario.
     *
     * @return nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del veterinario.
     *
     * @param nombreCompleto nombre completo del veterinario.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene la especialidad o área profesional del veterinario.
     *
     * @return especialidad del veterinario.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad profesional del veterinario.
     *
     * @param especialidad especialidad del veterinario.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la cuenta de usuario asociada al veterinario.
     *
     * @return cuenta del veterinario.
     */
    public Usuario getCuenta() {
        return cuenta;
    }

    /**
     * Asigna una cuenta de usuario al veterinario.
     *
     * @param cuenta entidad Usuario asociada.
     */
    public void setCuenta(Usuario cuenta) {
        this.cuenta = cuenta;
    }
}
