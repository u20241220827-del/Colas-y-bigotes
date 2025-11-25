package com.proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Busca un rol por su nombre exacto.
     *
     * @param nombre nombre del rol a buscar
     * @return el rol correspondiente, o null si no existe
     */
    Rol findByNombre(String nombre);
}
