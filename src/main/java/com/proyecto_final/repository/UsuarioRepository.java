package com.proyecto_final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     *
     * @param username nombre de usuario a consultar
     * @return un Optional que puede contener el usuario encontrado
     */
    Optional<Usuario> findByUsername(String username);
}
