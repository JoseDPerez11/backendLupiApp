package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.service.ProductoService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public GenericResponse listaProductosRecomendados() {
        return productoService.listarProductosRecomendados();
    }

    @GetMapping("/{idC}")
    public GenericResponse listaProductosPorCategoria(@PathVariable int idC) {
        return productoService.listarProductosPorCategoria(idC);
    }
}
