package com.proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca productos cuyo nombre contenga el texto proporcionado,
     * ignorando mayúsculas y minúsculas.
     *
     * @param nombre texto parcial a buscar dentro del nombre del producto
     * @return lista de productos que coinciden con el criterio de búsqueda
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
