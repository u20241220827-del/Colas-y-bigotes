package com.proyecto_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.model.Veterinario;
import com.proyecto_final.repository.UsuarioRepository;
import com.proyecto_final.repository.VeterinarioRepository;
import com.proyecto_final.service.CitaService;
import com.proyecto_final.service.VeterinarioService;

/**
 * Controlador encargado de gestionar todas las funcionalidades relacionadas
 * con el rol de Veterinario. Permite visualizar el panel principal,
 * consultar citas asignadas, ver detalles específicos y preparar la
 * prescripción para una cita.
 */
@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private CitaService citaService;

    /**
     * Muestra el panel principal del veterinario autenticado. Obtiene la cuenta
     * asociada, valida que exista un veterinario vinculado y lista todas las citas
     * programadas para él.
     *
     * @param model Modelo utilizado para enviar los datos a la vista.
     * @return Nombre de la plantilla correspondiente al panel del veterinario.
     */
    @GetMapping
    public String panelVeterinario(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Veterinario vet = veterinarioRepository.findByCuenta_Id(usuario.getId())
                .orElseThrow(() -> new RuntimeException("No existe un veterinario vinculado a este usuario"));

        List<Cita> citas = citaService.citasDeVeterinario(vet.getId());

        model.addAttribute("citas", citas);
        model.addAttribute("veterinario", vet);

        return "veterinario_vista";
    }

    /**
     * Lista todas las citas de un veterinario específico.
     *
     * @param vetId Identificador del veterinario.
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la plantilla con la lista de citas.
     */
    @GetMapping("/citas")
    public String citasVeterinario(@RequestParam Long vetId, Model model) {
        model.addAttribute("citas", veterinarioService.citasDeVeterinario(vetId));
        return "veterinario_cita_lista";
    }

    /**
     * Muestra el detalle de una cita atendida por el veterinario.
     *
     * @param id    Identificador de la cita.
     * @param model Modelo utilizado para enviar la información de la cita.
     * @return Nombre de la plantilla con el detalle de la cita.
     */
    @GetMapping("/citas/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("cita", veterinarioService.obtenerCita(id));
        return "veterinario_cita_detalle";
    }

    /**
     * Muestra el formulario que el veterinario utilizará para generar una
     * prescripción para una cita específica. Incluye la lista de productos disponibles.
     *
     * @param citaId Identificador de la cita a prescribir.
     * @param model  Modelo utilizado para enviar datos a la vista.
     * @return Nombre de la plantilla del formulario de prescripción.
     */
    @GetMapping("/prescribir/{citaId}")
    public String formularioPrescripcion(@PathVariable Long citaId, Model model) {
        model.addAttribute("citaId", citaId);
        model.addAttribute("productos", veterinarioService.obtenerProductos());
        return "veterinario_prescripcion_form";
    }
}
