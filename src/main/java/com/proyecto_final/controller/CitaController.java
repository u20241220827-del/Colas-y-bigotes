package com.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.service.CitaService;

/**
 * Controlador encargado de gestionar las operaciones relacionadas
 * con las citas: listado, detalle y cambio de estado.
 */
@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    /**
     * Lista todas las citas asociadas a un cliente específico.
     *
     * @param model     modelo utilizado para enviar la lista de citas a la vista.
     * @param clienteId identificador del cliente cuyas citas se desean consultar.
     * @return vista que muestra el listado de citas.
     */
    @GetMapping("/mis-citas")
    public String listarCitasCliente(Model model, @RequestParam Long clienteId) {
        model.addAttribute("citas", citaService.citasDeCliente(clienteId));
        return "cita_lista";
    }

    /**
     * Cambia el estado de una cita a "TERMINADA".
     *
     * @param id identificador de la cita que se desea actualizar.
     * @return redirección al panel del veterinario.
     */
    @GetMapping("/terminar/{id}")
    public String terminarCita(@PathVariable Long id) {
        citaService.cambiarEstado(id, "TERMINADA");
        return "redirect:/veterinario";
    }

    /**
     * Muestra el detalle de una cita específica.
     *
     * @param id    identificador de la cita a consultar.
     * @param model modelo utilizado para enviar la cita a la vista.
     * @return vista que muestra la información completa de la cita.
     */
    @GetMapping("/detalle/{id}")
    public String detalleCita(@PathVariable Long id, Model model) {
        model.addAttribute("cita", citaService.obtenerCita(id));
        return "cita_detalle";
    }
}
