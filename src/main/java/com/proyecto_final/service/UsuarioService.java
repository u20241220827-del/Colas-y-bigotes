package com.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Usuario;
import com.proyecto_final.repository.UsuarioRepository;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con la entidad {@link Usuario}.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario a consultar.
     * @return instancia de {@link Usuario} correspondiente al username proporcionado.
     * @throws RuntimeException si no se encuentra un usuario con el username especificado.
     */
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
