package com.proyecto_final.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.Mascota;
import com.proyecto_final.model.Prescripcion;
import com.proyecto_final.model.Producto;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.repository.MascotaRepository;
import com.proyecto_final.repository.PrescripcionRepository;
import com.proyecto_final.repository.ProductoRepository;
import com.proyecto_final.repository.CitaRepository;
import com.proyecto_final.service.CitaService;
import com.proyecto_final.service.MascotaService;
import com.proyecto_final.service.PrescripcionService;
import com.proyecto_final.service.UsuarioService;
import com.proyecto_final.service.VeterinarioService;

/**
 * Controlador encargado de gestionar todas las operaciones vinculadas
 * al usuario final dentro del sistema. Permite administrar mascotas,
 * citas, prescripciones y visualizar información relevante para el cliente.
 *
 * <p>Incluye funcionalidades como registrar mascotas, agendar citas,
 * listar información relacionada a mascotas y prescripciones, y realizar
 * el pago de prescripciones.</p>
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PrescripcionRepository prescripcionRepository;

    @Autowired 
    private PrescripcionService prescripcionService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired 
    private ProductoRepository productoRepository;

    /**
     * Muestra la vista principal del usuario.
     *
     * @return Nombre de la plantilla de la vista principal del usuario.
     */
    @GetMapping
    public String MostrarVistaUsuario() {
        return "user_vista";
    }

    /**
     * Muestra el formulario para registrar una nueva mascota.
     *
     * @param model Modelo para enviar los datos requeridos por la vista.
     * @return Nombre de la plantilla del formulario de registro de mascota.
     */
    @GetMapping("/mascotas/nueva")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("today", java.time.LocalDate.now());
        return "mascota_form";
    }

    /**
     * Registra una nueva mascota asociada al usuario autenticado.
     *
     * @param mascota    Datos de la mascota ingresados por el usuario.
     * @param principal  Información del usuario autenticado.
     * @return Redirección a la lista de mascotas del usuario.
     */
    @PostMapping("/mascotas/registrar")
    public String registrarMascota(@ModelAttribute Mascota mascota, Principal principal) {
        Long propietarioId = mascotaService.obtenerIdUsuario(principal.getName());
        mascotaService.registrarMascota(propietarioId, mascota);
        return "redirect:/usuario/mis-mascotas";
    }

    /**
     * Lista todas las mascotas registradas por el usuario autenticado.
     *
     * @param model     Modelo para enviar datos a la vista.
     * @param principal Información del usuario autenticado.
     * @return Nombre de la plantilla que muestra la lista de mascotas.
     */
    @GetMapping("/mis-mascotas")
    public String listarMisMascotas(Model model, Principal principal) {
        Long usuarioId = mascotaService.obtenerIdUsuario(principal.getName());
        model.addAttribute("mascotas", mascotaService.listarMascotasDeUsuario(usuarioId));
        return "mascota_lista";
    }

    /**
     * Muestra el detalle de una mascota específica.
     *
     * @param id    Identificador de la mascota.
     * @param model Modelo para enviar datos a la vista.
     * @return Nombre de la plantilla de detalles de la mascota.
     */
    @GetMapping("/detalle/{id}")
    public String detalleMascota(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaService.obtenerMascota(id);
        model.addAttribute("mascota", mascota);
        return "mascota_detalle";
    }

    /**
     * Muestra el formulario para agendar una cita respecto a una mascota específica.
     *
     * @param mascotaId Identificador de la mascota.
     * @param principal Usuario autenticado.
     * @param model     Modelo para enviar datos a la vista.
     * @return Nombre de la plantilla del formulario de agendamiento de cita.
     */
    @GetMapping("/citas/agendar/{mascotaId}")
    public String mostrarFormulario(@PathVariable Long mascotaId, Principal principal, Model model) {
        Usuario cliente = usuarioService.buscarPorUsername(principal.getName());
        Mascota mascota = mascotaService.obtenerMascota(mascotaId);

        if (!mascota.getPropietario().getId().equals(cliente.getId())) {
            model.addAttribute("error", "No puedes agendar una cita para una mascota que no es tuya.");
            return "error";
        }

        Cita cita = new Cita();
        cita.setMascota(mascota);
        cita.setCliente(cliente);

        model.addAttribute("cita", cita);
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        return "cita_form";
    }

    /**
     * Muestra el detalle de una prescripción.
     *
     * @param id    Identificador de la prescripción.
     * @param model Modelo para enviar datos a la vista.
     * @return Nombre de la plantilla del detalle de prescripción.
     */
    @GetMapping("/prescripcion/detalle/{id}")
    public String detallePrescripcion(@PathVariable Long id, Model model) {
        Prescripcion prescripcion = prescripcionService.obtenerPrescripcionPorId(id);
        model.addAttribute("prescripcion", prescripcion);
        return "prescripcion_detalle";
    }

    /**
     * Lista las citas relacionadas a una mascota específica.
     *
     * @param idMascota Identificador de la mascota.
     * @param model     Modelo para enviar datos a la vista.
     * @return Nombre de la plantilla que lista las citas.
     */
    @GetMapping("/citas/mascota/{idMascota}")
    public String listarCitasPorMascota(@PathVariable Long idMascota, Model model) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        var citas = citaRepository.findByMascotaId(idMascota);

        model.addAttribute("mascota", mascota);
        model.addAttribute("citas", citas);

        return "cita_lista";
    }

    /**
     * Procesa el pago de una prescripción y descuenta el stock del producto asociado.
     *
     * @param id                  Identificador de la prescripción.
     * @param redirectAttributes  Atributos para mensajes flash.
     * @return Redirección al detalle de la prescripción pagada.
     */
    @PostMapping("/prescripcion/pagar/{id}")
    public String pagarPrescripcion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Prescripcion prescripcion = prescripcionService.obtenerPrescripcionPorId(id);
        Producto producto = prescripcion.getProducto();

        if (producto != null) {
            int nuevoStock = producto.getStock() - prescripcion.getCantidad();
            producto.setStock(nuevoStock >= 0 ? nuevoStock : 0);
            productoRepository.save(producto);
        }

        prescripcionService.marcarPagada(id);
        redirectAttributes.addFlashAttribute("exito", "Prescripción pagada con éxito");

        return "redirect:/usuario/prescripcion/detalle/" + id;
    }

    /**
     * Lista todas las prescripciones asociadas a una mascota específica.
     *
     * @param idMascota Identificador de la mascota.
     * @param model     Modelo para enviar datos a la vista.
     * @return Nombre de la plantilla que lista las prescripciones.
     */
    @GetMapping("/prescripciones/{idMascota}")
    public String listarPrescripciones(@PathVariable Long idMascota, Model model) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        var prescripciones = prescripcionRepository.findByMascotaId(idMascota);

        model.addAttribute("mascota", mascota);
        model.addAttribute("prescripciones", prescripciones);

        return "prescripcion_lista";
    }

    /**
     * Procesa el guardado de una cita agendada por el usuario.
     *
     * @param cita     Datos de la cita enviados desde el formulario.
     * @param principal Información del usuario autenticado.
     * @param model     Modelo para mensajes o recarga de datos.
     * @return Redirección al panel del usuario o recarga del formulario en caso de error.
     */
    @PostMapping("/agendar")
    public String guardarCita(@ModelAttribute("cita") Cita cita, Principal principal, Model model) {
        try {
            Long clienteId = cita.getCliente().getId();
            Long mascotaId = cita.getMascota().getId();
            Long veterinarioId = cita.getVeterinario().getId();

            citaService.agendar(clienteId, mascotaId, veterinarioId, cita.getFechaHora());
            return "redirect:/usuario";

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
            return "cita_form";
        }
    }
}
