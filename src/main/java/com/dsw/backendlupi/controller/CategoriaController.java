package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.service.CategoriaService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public GenericResponse listarCategoriasActivas() {
        return categoriaService.listarCategoriasActivas();
    }

}
