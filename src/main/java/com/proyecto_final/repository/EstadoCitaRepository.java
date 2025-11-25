package com.proyecto_final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_final.model.EstadoCita;

@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long> {

    /**
     * Busca un estado de cita por su nombre.
     *
     * @param nombre nombre del estado (ejemplo: PROGRAMADA, EN_CURSO, TERMINADA)
     * @return un Optional con el estado encontrado o vacío si no existe
     */
    Optional<EstadoCita> findByNombre(String nombre);
}
