package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.entity.Cliente;
import com.dsw.backendlupi.service.ClienteService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService cService;

    @PostMapping
    public GenericResponse save(@Valid @RequestBody Cliente cliente) {
        return cService.save(cliente);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @Valid @RequestBody Cliente cliente) {
        cliente.setId(id);
        return cService.save(cliente);
    }

}
