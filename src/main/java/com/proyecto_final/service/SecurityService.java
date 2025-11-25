package com.proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Usuario;
import com.proyecto_final.repository.UsuarioRepository;

import java.util.Collection;

/**
 * Servicio encargado de la carga de usuarios para el proceso de autenticación
 * de Spring Security. Implementa UserDetailsService para permitir la
 * obtención de usuarios desde la base de datos y la conversión de roles
 * a autoridades de Spring Security.
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga un usuario por su nombre de usuario y lo convierte en un objeto
     * UserDetails que Spring Security utiliza para la autenticación.
     *
     * @param nombreUsuario nombre de usuario ingresado durante el login.
     * @return UserDetails con el usuario autenticado, contraseña y roles.
     * @throws UsernameNotFoundException si el usuario no existe en la base de datos.
     */
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Collection<SimpleGrantedAuthority> authorities =
                usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                        .toList();

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();
    }
}
