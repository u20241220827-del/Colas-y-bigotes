package com.proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    /**
     * Obtiene la lista de mascotas pertenecientes a un propietario específico.
     *
     * @param propietarioId identificador del usuario propietario de las mascotas
     * @return lista de mascotas asociadas al propietario
     */
    List<Mascota> findByPropietarioId(Long propietarioId);
}
