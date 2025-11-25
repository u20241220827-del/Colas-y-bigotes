package com.proyecto_final.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_final.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    /**
     * Obtiene todas las citas asociadas a un cliente específico.
     *
     * @param clienteId identificador del cliente
     * @return lista de citas del cliente
     */
    List<Cita> findByClienteId(Long clienteId);

    /**
     * Obtiene todas las citas asociadas a una mascota en particular.
     *
     * @param mascotaId identificador de la mascota
     * @return lista de citas de la mascota
     */
    List<Cita> findByMascotaId(Long mascotaId);

    /**
     * Obtiene todas las citas asignadas a un veterinario específico.
     *
     * @param veterinarioId identificador del veterinario
     * @return lista de citas del veterinario
     */
    List<Cita> findByVeterinarioId(Long veterinarioId);

    /**
     * Obtiene las citas según un estado particular (ejemplo: programada, en curso, terminada).
     *
     * @param estadoId identificador del estado
     * @return lista de citas filtradas por estado
     */
    List<Cita> findByEstadoId(Long estadoId);

    /**
     * Verifica si una mascota ya tiene una cita programada en una fecha y hora específica.
     *
     * @param mascotaId identificador de la mascota
     * @param fechaHora fecha y hora de la cita
     * @return true si existe una cita en ese horario, false en caso contrario
     */
    boolean existsByMascotaIdAndFechaHora(Long mascotaId, LocalDateTime fechaHora);

    /**
     * Verifica si un veterinario tiene alguna cita programada en un horario específico.
     *
     * @param veterinarioId identificador del veterinario
     * @param fechaHora fecha y hora de la cita
     * @return true si el horario ya está ocupado, false en caso contrario
     */
    boolean existsByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);
}
