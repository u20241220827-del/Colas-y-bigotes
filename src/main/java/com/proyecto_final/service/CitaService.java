package com.proyecto_final.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.EstadoCita;
import com.proyecto_final.model.Mascota;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.model.Veterinario;
import com.proyecto_final.repository.CitaRepository;
import com.proyecto_final.repository.EstadoCitaRepository;
import com.proyecto_final.repository.MascotaRepository;
import com.proyecto_final.repository.UsuarioRepository;
import com.proyecto_final.repository.VeterinarioRepository;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las citas
 * veterinarias, incluyendo agendamiento, actualización de estado y consultas.
 */
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    /**
     * Agenda una nueva cita, validando que la fecha sea válida, que la mascota
     * exista, que el cliente y veterinario existan, y que no haya conflictos de agenda.
     *
     * @param clienteId      ID del cliente que solicita la cita.
     * @param mascotaId      ID de la mascota a la cual se asigna la cita.
     * @param veterinarioId  ID del veterinario asignado.
     * @param fechaHora      Fecha y hora de la cita.
     * @return Cita registrada en base de datos.
     */
    public Cita agendar(Long clienteId, Long mascotaId, Long veterinarioId, LocalDateTime fechaHora) {

        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se pueden agendar citas en fechas u horas anteriores a la actual.");
        }

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada."));

        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        Veterinario vet = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado."));

        if (citaRepository.existsByMascotaIdAndFechaHora(mascotaId, fechaHora)) {
            throw new RuntimeException("La mascota ya tiene una cita en esa fecha y hora.");
        }

        if (citaRepository.existsByVeterinarioIdAndFechaHora(veterinarioId, fechaHora)) {
            throw new RuntimeException("El veterinario ya tiene una cita en esa fecha y hora.");
        }

        EstadoCita estado = estadoCitaRepository.findByNombre("PROGRAMADA")
                .orElseThrow(() -> new RuntimeException("Estado PROGRAMADA no existe."));

        Cita cita = new Cita();
        cita.setFechaHora(fechaHora);
        cita.setMascota(mascota);
        cita.setCliente(cliente);
        cita.setVeterinario(vet);
        cita.setEstado(estado);

        return citaRepository.save(cita);
    }

    /**
     * Obtiene todas las citas pertenecientes a un cliente específico.
     *
     * @param clienteId ID del cliente.
     * @return Lista de citas del cliente.
     */
    public List<Cita> citasDeCliente(Long clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    /**
     * Obtiene todas las citas asignadas a un veterinario específico.
     *
     * @param vetId ID del veterinario.
     * @return Lista de citas del veterinario.
     */
    public List<Cita> citasDeVeterinario(Long vetId) {
        return citaRepository.findByVeterinarioId(vetId);
    }

    /**
     * Cambia el estado actual de una cita.
     *
     * @param citaId      ID de la cita.
     * @param nuevoEstado Nombre del nuevo estado a asignar.
     * @return Cita actualizada.
     */
    public Cita cambiarEstado(Long citaId, String nuevoEstado) {

        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        EstadoCita estado = estadoCitaRepository.findByNombre(nuevoEstado)
                .orElseThrow(() -> new RuntimeException("Estado no válido"));

        cita.setEstado(estado);

        return citaRepository.save(cita);
    }

    /**
     * Obtiene un estado de cita por su nombre.
     *
     * @param nombre Nombre del estado.
     * @return EstadoCita encontrado.
     */
    public EstadoCita obtenerEstadoPorNombre(String nombre) {
        return estadoCitaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
    }

    /**
     * Obtiene una cita por su ID.
     *
     * @param id ID de la cita.
     * @return Cita encontrada.
     */
    public Cita obtenerCita(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }
}
