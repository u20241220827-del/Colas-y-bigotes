package com.proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.model.Cita;
import com.proyecto_final.model.Producto;
import com.proyecto_final.model.Rol;
import com.proyecto_final.model.Usuario;
import com.proyecto_final.model.Veterinario;
import com.proyecto_final.repository.CitaRepository;
import com.proyecto_final.repository.ProductoRepository;
import com.proyecto_final.repository.RolRepository;
import com.proyecto_final.repository.UsuarioRepository;
import com.proyecto_final.repository.VeterinarioRepository;

/**
 * Servicio que gestiona las operaciones relacionadas con los veterinarios,
 * incluyendo registro, ascensos, consultas de información asociada y la
 * administración general de los recursos vinculados.
 */
@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    /**
     * Registra un nuevo veterinario sin asociarlo a una cuenta de usuario.
     *
     * @param nombre nombre completo del veterinario.
     * @param especialidad área de especialidad que desempeña.
     * @return instancia persistida de {@link Veterinario}.
     */
    public Veterinario registrarVeterinario(String nombre, String especialidad) {

        Veterinario vet = new Veterinario();
        vet.setNombreCompleto(nombre);
        vet.setEspecialidad(especialidad);

        return veterinarioRepository.save(vet);
    }

    /**
     * Asciende un usuario existente al rol de veterinario. Este proceso asigna
     * el rol correspondiente y genera un registro en la tabla veterinario.
     *
     * @param usuarioId identificador del usuario a ascender.
     * @param nombreCompleto nombre completo del nuevo veterinario.
     * @param especialidad especialidad que desempeñará el veterinario.
     * @throws RuntimeException si el usuario no existe.
     */
    public void ascenderAVeterinario(Long usuarioId, String nombreCompleto, String especialidad) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rolVet = rolRepository.findByNombre("ROLE_VETERINARIO");

        if (!usuario.getRoles().contains(rolVet)) {
            usuario.getRoles().add(rolVet);
            usuarioRepository.save(usuario);
        }

        Veterinario vet = new Veterinario();
        vet.setNombreCompleto(nombreCompleto);
        vet.setEspecialidad(especialidad);
        vet.setCuenta(usuario);

        veterinarioRepository.save(vet);
    }

    /**
     * Obtiene un listado completo de los veterinarios registrados.
     *
     * @return lista de veterinarios disponibles.
     */
    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepository.findAll();
    }

    /**
     * Recupera todas las citas asociadas a un veterinario específico.
     *
     * @param vetId identificador del veterinario.
     * @return lista de citas asignadas.
     */
    public List<Cita> citasDeVeterinario(Long vetId) {
        return citaRepository.findByVeterinarioId(vetId);
    }

    /**
     * Obtiene los detalles de una cita específica.
     *
     * @param id identificador de la cita.
     * @return instancia de {@link Cita}.
     * @throws RuntimeException si la cita no existe.
     */
    public Cita obtenerCita(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    /**
     * Recupera todos los productos disponibles para ser utilizados en prescripciones.
     *
     * @return lista de productos.
     */
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
}
