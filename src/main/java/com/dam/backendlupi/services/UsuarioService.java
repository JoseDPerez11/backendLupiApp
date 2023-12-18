package com.dam.backendlupi.services;

import com.dam.backendlupi.entity.Usuario;
import com.dam.backendlupi.repository.UsuarioRepository;
import com.dam.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.dam.backendlupi.utils.Global.*;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //inicio de sesión
    public GenericResponse<Usuario> login(String email, String contrasenia) {
        Optional<Usuario> optionalUsuario = usuarioRepository.login(email, contrasenia);

        if (optionalUsuario.isPresent()) {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK, "Haz iniciado sesión correctamente", optionalUsuario.get());
        } else {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING, "Error, ese usuario no existe", new Usuario());
        }

    }

}
