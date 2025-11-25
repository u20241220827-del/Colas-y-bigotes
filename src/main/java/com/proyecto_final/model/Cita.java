package com.proyecto_final.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/**
 * Representa una cita veterinaria dentro del sistema.
 * Contiene información sobre la fecha y hora, la mascota
 * involucrada, el cliente que agenda, el veterinario asignado
 * y el estado actual de la cita.
 */
@Entity
public class Cita {

    /**
     * Identificador único de la cita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha y hora programada para la cita.
     */
    private LocalDateTime fechaHora;

    /**
     * Mascota asociada a la cita.
     */
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    /**
     * Cliente que agenda la cita.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    /**
     * Veterinario asignado para atender la cita.
     */
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    /**
     * Estado actual de la cita (programada, en curso, terminada, etc.).
     */
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoCita estado;

    /**
     * Obtiene el identificador de la cita.
     *
     * @return el id de la cita
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la cita.
     *
     * @param id nuevo identificador de la cita
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora programada para la cita.
     *
     * @return fecha y hora de la cita
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la cita.
     *
     * @param fechaHora nueva fecha y hora de la cita
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la mascota asociada a la cita.
     *
     * @return la mascota
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Establece la mascota de la cita.
     *
     * @param mascota nueva mascota asociada
     */
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    /**
     * Obtiene el cliente que agenda la cita.
     *
     * @return cliente asociado
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente que agenda la cita.
     *
     * @param cliente nuevo cliente asociado
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el veterinario asignado a la cita.
     *
     * @return veterinario asignado
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Establece el veterinario asignado a la cita.
     *
     * @param veterinario nuevo veterinario asociado
     */
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    /**
     * Obtiene el estado actual de la cita.
     *
     * @return estado de la cita
     */
    public EstadoCita getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la cita.
     *
     * @param estado nuevo estado de la cita
     */
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}
