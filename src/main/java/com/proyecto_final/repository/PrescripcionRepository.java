package com.proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Prescripcion;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {

    /**
     * Obtiene todas las prescripciones asociadas a una mascota específica.
     *
     * @param mascotaId identificador de la mascota
     * @return lista de prescripciones vinculadas a la mascota
     */
    List<Prescripcion> findByMascotaId(Long mascotaId);

    /**
     * Obtiene todas las prescripciones creadas por un veterinario.
     *
     * @param veterinarioId identificador del veterinario
     * @return lista de prescripciones generadas por el veterinario
     */
    List<Prescripcion> findByVeterinarioId(Long veterinarioId);

    /**
     * Obtiene todas las prescripciones asociadas a una cita específica.
     *
     * @param citaId identificador de la cita
     * @return lista de prescripciones vinculadas a la cita
     */
    List<Prescripcion> findByCitaId(Long citaId);
}
