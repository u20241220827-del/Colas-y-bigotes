package com.proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.proyecto_final.model.Usuario;
import com.proyecto_final.service.RegistroService;

/**
 * Controlador encargado de gestionar el proceso de autenticación
 * y registro de nuevos usuarios del sistema.
 */
@Controller
public class AuthController {

    @Autowired
    private RegistroService registroService;

    /**
     * Muestra la vista del formulario de inicio de sesión.
     *
     * @return nombre de la vista de login.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Muestra el formulario de registro para crear un nuevo usuario.
     *
     * @param model modelo utilizado para enviar una instancia de Usuario a la vista.
     * @return nombre de la vista de registro.
     */
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    /**
     * Procesa la creación de un nuevo usuario.
     *
     * @param usuario objeto Usuario recibido desde el formulario.
     * @param result resultado de la validación del formulario.
     * @param model modelo utilizado para enviar mensajes de error en caso necesario.
     * @return redirección al login si el registro es exitoso; de lo contrario, retorna a la vista de registro.
     */
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, BindingResult result, Model model) {

        try {
            registroService.registrarUsuario(usuario);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }

        return "redirect:/login?registrado";
    }
}
