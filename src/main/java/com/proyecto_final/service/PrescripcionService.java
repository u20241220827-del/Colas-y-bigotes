package com.proyecto_final.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.Prescripcion;
import com.proyecto_final.model.Producto;
import com.proyecto_final.repository.CitaRepository;
import com.proyecto_final.repository.PrescripcionRepository;
import com.proyecto_final.repository.ProductoRepository;

/**
 * Servicio encargado de gestionar la creación, consulta y actualización de
 * prescripciones médicas asociadas a citas veterinarias.
 */
@Service
public class PrescripcionService {

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Crea una nueva prescripción vinculada a una cita y un producto específico.
     *
     * @param citaId       ID de la cita asociada.
     * @param productoId   ID del producto indicado en la prescripción.
     * @param cantidad     Cantidad del producto a recetar.
     * @param indicaciones Indicaciones o instrucciones de uso.
     * @return Prescripción creada y almacenada en la base de datos.
     * @throws RuntimeException si la cita o el producto no existen.
     */
    public Prescripcion crearPrescripcion(Long citaId, Long productoId, Integer cantidad, String indicaciones) {

        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Prescripcion p = new Prescripcion();
        p.setCita(cita);
        p.setMascota(cita.getMascota());
        p.setVeterinario(cita.getVeterinario());
        p.setProducto(producto);
        p.setCantidad(cantidad);
        p.setIndicaciones(indicaciones);
        p.setFecha(LocalDate.now());
        p.setPagada(false);

        return prescripcionRepository.save(p);
    }

    /**
     * Obtiene la lista de prescripciones pertenecientes a una mascota.
     *
     * @param mascotaId ID de la mascota.
     * @return Lista de prescripciones asociadas a la mascota.
     */
    public List<Prescripcion> prescripcionesDeMascota(Long mascotaId) {
        return prescripcionRepository.findByMascotaId(mascotaId);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto.
     * @return Producto encontrado.
     * @throws RuntimeException si el producto no existe.
     */
    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    /**
     * Lista todos los productos existentes.
     *
     * @return Lista completa de productos almacenados.
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    /**
     * Obtiene una prescripción por su ID.
     *
     * @param id ID de la prescripción.
     * @return Prescripción encontrada.
     * @throws RuntimeException si la prescripción no existe.
     */
    public Prescripcion obtenerPrescripcionPorId(Long id) {
        return prescripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescripción no encontrada"));
    }

    /**
     * Marca una prescripción como pagada.
     *
     * @param id ID de la prescripción a actualizar.
     * @return Prescripción actualizada con estado pagado.
     * @throws RuntimeException si la prescripción no existe.
     */
    public Prescripcion marcarPagada(Long id) {

        Prescripcion p = prescripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescripción no encontrada"));

        p.setPagada(true);

        return prescripcionRepository.save(p);
    }

}
