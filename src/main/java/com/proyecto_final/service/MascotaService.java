package com.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Mascota;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.repository.MascotaRepository;
import com.proyecto_final.repository.UsuarioRepository;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las mascotas,
 * incluyendo registro, consulta y asociación con propietarios.
 */
@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra una nueva mascota asociándola a un propietario específico.
     *
     * @param propietarioId ID del usuario propietario de la mascota.
     * @param mascota       Objeto Mascota a registrar.
     * @return Mascota registrada y persistida en la base de datos.
     * @throws RuntimeException si el propietario no existe.
     */
    public Mascota registrarMascota(Long propietarioId, Mascota mascota) {
        Usuario propietario = usuarioRepository.findById(propietarioId)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        mascota.setPropietario(propietario);
        return mascotaRepository.save(mascota);
    }

    /**
     * Obtiene la lista de mascotas pertenecientes a un usuario.
     *
     * @param propietarioId ID del propietario.
     * @return Lista de mascotas del propietario.
     */
    public List<Mascota> listarMascotasDeUsuario(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    /**
     * Obtiene una mascota por su ID.
     *
     * @param id ID de la mascota.
     * @return Mascota encontrada.
     * @throws RuntimeException si la mascota no existe.
     */
    public Mascota obtenerMascota(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    /**
     * Obtiene el ID de un usuario a partir de su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return ID del usuario.
     * @throws RuntimeException si el usuario no existe.
     */
    public Long obtenerIdUsuario(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                .getId();
    }
}
