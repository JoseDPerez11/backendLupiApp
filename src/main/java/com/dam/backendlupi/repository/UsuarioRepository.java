package com.dam.backendlupi.repository;

import com.dam.backendlupi.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("Select U from Usuario U where U.email=:correo AND U.clave=:password")
    Optional<Usuario> login(String correo, String password);
}
