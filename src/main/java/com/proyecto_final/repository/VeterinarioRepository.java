package com.proyecto_final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    /**
     * Busca un veterinario según el ID de la cuenta asociada.
     *
     * @param usuarioId ID del usuario que representa la cuenta asociada al veterinario
     * @return Optional que puede contener el veterinario encontrado
     */
    Optional<Veterinario> findByCuentaId(Long usuarioId);

    /**
     * Variante equivalente de búsqueda usando la sintaxis basada en propiedad anidada.
     *
     * @param usuarioId ID del usuario asociado a la cuenta del veterinario
     * @return Optional con el veterinario encontrado si existe
     */
    Optional<Veterinario> findByCuenta_Id(Long usuarioId);
}
