package com.proyecto_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de gestionar la página principal del sistema.
 */
@Controller
public class HomeController {

    /**
     * Muestra la vista inicial del sistema.
     *
     * @return nombre de la vista de inicio.
     */
    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
}
