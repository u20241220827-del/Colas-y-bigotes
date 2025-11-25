package com.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Rol;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.repository.RolRepository;
import com.proyecto_final.repository.UsuarioRepository;

/**
 * Servicio encargado del registro de usuarios dentro del sistema,
 * incluyendo validaciones, encriptación de contraseñas y asignación de roles.
 */
@Service
public class RegistroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario validando que el nombre de usuario no exista,
     * encriptando la contraseña y asignando el rol por defecto "ROLE_USUARIO".
     *
     * @param usuario Objeto Usuario con los datos a registrar.
     * @return Usuario registrado y persistido en la base de datos.
     * @throws RuntimeException si el nombre de usuario ya está en uso.
     */
    public Usuario registrarUsuario(Usuario usuario) {

        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol USUARIO
        Rol rolUsuario = rolRepository.findByNombre("ROLE_USUARIO");

        usuario.getRoles().add(rolUsuario);

        return usuarioRepository.save(usuario);
    }
}
