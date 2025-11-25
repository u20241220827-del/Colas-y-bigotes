package com.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.Prescripcion;
import com.proyecto_final.model.Producto;
import com.proyecto_final.service.CitaService;
import com.proyecto_final.service.PrescripcionService;

/**
 * Controlador encargado de gestionar la creación y consulta de prescripciones
 * asociadas a citas veterinarias.
 */
@Controller
@RequestMapping("/prescripciones")
public class PrescripcionController {

    @Autowired
    private PrescripcionService prescripcionService;
    
    @Autowired
    private CitaService citaService;

    /**
     * Muestra el formulario para crear una prescripción asociada a una cita,
     * identificado mediante un parámetro en la URL.
     *
     * @param citaId identificador de la cita.
     * @param model  modelo para enviar los datos necesarios a la vista.
     * @return vista del formulario de prescripción.
     */
    @GetMapping("/crear/{citaId}")
    public String mostrarFormulario(@PathVariable Long citaId, Model model) {
        Cita cita = citaService.obtenerCita(citaId);
        List<Producto> productos = prescripcionService.listarProductos();

        model.addAttribute("cita", cita);
        model.addAttribute("productos", productos);
        model.addAttribute("prescripcion", new Prescripcion());

        return "prescripcion_form";
    }

    /**
     * Muestra el formulario de creación de prescripción utilizando un parámetro de consulta.
     *
     * @param citaId identificador de la cita.
     * @param model  modelo para enviar los datos necesarios a la vista.
     * @return vista del formulario de prescripción.
     */
    @GetMapping("/crear")
    public String mostrarFormularioQuery(@RequestParam Long citaId, Model model) {
        Cita cita = citaService.obtenerCita(citaId);
        List<Producto> productos = prescripcionService.listarProductos();

        model.addAttribute("cita", cita);
        model.addAttribute("productos", productos);
        model.addAttribute("prescripcion", new Prescripcion());

        return "prescripcion_form";
    }

    /**
     * Guarda una nueva prescripción con los datos proporcionados y marca la cita como terminada.
     *
     * @param citaId       identificador de la cita asociada.
     * @param productoId   identificador del producto recetado.
     * @param cantidad     cantidad del producto.
     * @param indicaciones indicaciones médicas.
     * @param model        modelo para vista (no usado en este método).
     * @return redirección al panel del veterinario.
     */
    @PostMapping("/guardar")
    public String guardarPrescripcion(
            @RequestParam Long citaId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam String indicaciones,
            Model model) {

        prescripcionService.crearPrescripcion(citaId, productoId, cantidad, indicaciones);
        citaService.cambiarEstado(citaId, "TERMINADA");

        return "redirect:/veterinario";
    }

    /**
     * Lista todas las prescripciones asociadas a una mascota específica.
     *
     * @param mascotaId identificador de la mascota.
     * @param model     modelo para enviar la lista de prescripciones a la vista.
     * @return vista con el listado de prescripciones.
     */
    @GetMapping("/mascota/{mascotaId}")
    public String prescripcionesPorMascota(@PathVariable Long mascotaId, Model model) {
        List<Prescripcion> prescripciones = prescripcionService.prescripcionesDeMascota(mascotaId);
        model.addAttribute("prescripciones", prescripciones);
        return "prescripciones/lista";
    }
}
