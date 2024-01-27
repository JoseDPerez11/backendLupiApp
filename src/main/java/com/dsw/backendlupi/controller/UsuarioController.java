package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.entity.Usuario;
import com.dsw.backendlupi.service.UsuarioService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService uService;

    @PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("pass");
        return uService.login(email, contrasenia);
    }

}
