package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email=:correo AND u.clave=:contrasenia")
    Optional<Usuario> login(String correo, String contrasenia);
}
