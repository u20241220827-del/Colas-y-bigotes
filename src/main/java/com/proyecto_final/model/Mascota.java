package com.proyecto_final.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/**
 * Representa una mascota registrada en el sistema.
 * Incluye datos básicos como nombre, especie, raza, fecha de nacimiento
 * y el propietario al que pertenece.
 */
@Entity
public class Mascota {

    /**
     * Identificador único de la mascota.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la mascota.
     */
    private String nombre;

    /**
     * Especie de la mascota (ejemplo: perro, gato).
     */
    private String especie;

    /**
     * Raza de la mascota.
     */
    private String raza;

    /**
     * Fecha de nacimiento de la mascota.
     */
    private LocalDate fechaNacimiento;

    /**
     * Propietario de la mascota.
     */
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario propietario;

    /**
     * Obtiene el identificador de la mascota.
     *
     * @return id de la mascota
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la mascota.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la mascota.
     *
     * @return nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la mascota.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especie de la mascota.
     *
     * @return especie de la mascota
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie de la mascota.
     *
     * @param especie nueva especie
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene la raza de la mascota.
     *
     * @return raza de la mascota
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza de la mascota.
     *
     * @param raza nueva raza
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Obtiene la fecha de nacimiento de la mascota.
     *
     * @return fecha de nacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento de la mascota.
     *
     * @param fechaNacimiento nueva fecha de nacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el propietario de la mascota.
     *
     * @return propietario de la mascota
     */
    public Usuario getPropietario() {
        return propietario;
    }

    /**
     * Establece el propietario de la mascota.
     *
     * @param propietario nuevo propietario
     */
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }
}
