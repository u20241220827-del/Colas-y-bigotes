package com.proyecto_final.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Representa un usuario dentro del sistema.
 * Incluye credenciales de acceso y los roles asociados
 * que determinan sus permisos.
 */
@Entity
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario utilizado para autenticación.
     */
    private String username;

    /**
     * Contraseña cifrada del usuario.
     */
    private String password;

    /**
     * Roles asociados al usuario.
     * Se cargan inmediatamente debido al FetchType.EAGER.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Collection<Rol> roles = new ArrayList<>();

    /**
     * Obtiene el identificador del usuario.
     *
     * @return id del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return username del usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username nuevo nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña cifrada
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password nueva contraseña cifrada
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene los roles asignados al usuario.
     *
     * @return colección de roles
     */
    public Collection<Rol> getRoles() {
        return roles;
    }

    /**
     * Establece los roles asignados al usuario.
     *
     * @param roles nueva colección de roles
     */
    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }
}
