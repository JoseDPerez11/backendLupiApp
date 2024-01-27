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
    public GenericResponse guardarUsuario(Usuario usuario) {
        // Buscar el usuario por ID en el repositorio
        Optional<Usuario> optionalUsuario = this.uRepository.findById(usuario.getId());

        // Obtener el ID del usuario si existe, de lo contrario, establecerlo en 0
        int idf = optionalUsuario.isPresent() ? optionalUsuario.get().getId() : 0;

        // Verificar si el usuario existe en la base de datos
        if (idf == 0) {
            // Si el usuario no existe, se registra y se devuelve una respuesta positiva
            return new GenericResponse(
                    TIPO_DATA, RPTA_OK, "Usuario registrado correctamente",
                    this.uRepository.save(usuario));
        } else {
            // Si el usuario ya existe, se actualizan los datos y se devuelve una respuesta positiva
            return new GenericResponse(
                    TIPO_DATA, RPTA_OK, "Datos del usuario actualizado",
                    this.uRepository.save(usuario));
        }
    }

}
