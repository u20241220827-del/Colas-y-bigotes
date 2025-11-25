package com.proyecto_final.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/**
 * Representa una prescripción realizada durante una cita veterinaria.
 * Incluye información sobre la cita, la mascota atendida, el veterinario,
 * el producto recetado, la cantidad, indicaciones y fecha de emisión.
 */
@Entity
public class Prescripcion {

    /**
     * Identificador único de la prescripción.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cita asociada a la prescripción.
     */
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    /**
     * Mascota a la que se le aplica la prescripción.
     */
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    /**
     * Veterinario que emite la prescripción.
     */
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    /**
     * Producto recetado dentro de la prescripción.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    /**
     * Cantidad del producto recetado.
     */
    private Integer cantidad;

    /**
     * Indicaciones específicas para la administración del producto.
     */
    private String indicaciones;

    /**
     * Fecha en que se emitió la prescripción.
     */
    private LocalDate fecha;

    /**
     * Indica si la prescripción ya fue pagada.
     */
    private boolean pagada = false;

    /**
     * Obtiene el identificador de la prescripción.
     *
     * @return id de la prescripción
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la prescripción.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la cita asociada a la prescripción.
     *
     * @return cita asociada
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Establece la cita asociada a la prescripción.
     *
     * @param cita nueva cita asociada
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Obtiene la mascota relacionada con la prescripción.
     *
     * @return mascota asociada
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Establece la mascota relacionada con la prescripción.
     *
     * @param mascota nueva mascota asociada
     */
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    /**
     * Obtiene el veterinario que emitió la prescripción.
     *
     * @return veterinario
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Establece el veterinario que emite la prescripción.
     *
     * @param veterinario nuevo veterinario asociado
     */
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    /**
     * Obtiene el producto recetado.
     *
     * @return producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto recetado.
     *
     * @param producto nuevo producto asociado
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad del producto recetado.
     *
     * @return cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto recetado.
     *
     * @param cantidad nueva cantidad
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene las indicaciones de uso del producto.
     *
     * @return texto de indicaciones
     */
    public String getIndicaciones() {
        return indicaciones;
    }

    /**
     * Establece las indicaciones de uso del producto.
     *
     * @param indicaciones nuevas indicaciones
     */
    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    /**
     * Obtiene la fecha en que fue emitida la prescripción.
     *
     * @return fecha de emisión
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se emitió la prescripción.
     *
     * @param fecha nueva fecha de emisión
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Verifica si la prescripción ya fue pagada.
     *
     * @return true si está pagada, false en caso contrario
     */
    public boolean isPagada() {
        return pagada;
    }

    /**
     * Establece el estado de pago de la prescripción.
     *
     * @param pagada nuevo valor de estado de pago
     */
    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}
