package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.Usuario;
import com.dsw.backendlupi.repository.UsuarioRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository uRepository;

    // Iniciar sesión
    public GenericResponse<Usuario> login(String email, String contrasenia) {
        Optional<Usuario> usuarioOptional = uRepository.login(email, contrasenia);
        if (usuarioOptional.isPresent()) {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK, "Has iniciado sesión correctamente", usuarioOptional.get());
        } else {
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING, "Credenciales inválidas, usuario no existe", new Usuario());
        }
    }

    // Guardar credenciales
    

}
