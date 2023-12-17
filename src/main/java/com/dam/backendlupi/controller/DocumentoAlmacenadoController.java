package com.dam.backendlupi.controller;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import com.dam.backendlupi.services.DocumentoAlmacenadoService;
import com.dam.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/documento-almacenado")
public class DocumentoAlmacenadoController {

    @Autowired
    private DocumentoAlmacenadoService documentoAlmacenadoService;

    @GetMapping
    public GenericResponse list() {return documentoAlmacenadoService.list();}

    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {return null;}

    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return documentoAlmacenadoService.downloadByFileName(fileName, request);
    }

    @PostMapping
    public GenericResponse save(@ModelAttribute DocumentoAlmacenado obj) {
        return documentoAlmacenadoService.save(obj);
    }

    public GenericResponse update(Long aLong, DocumentoAlmacenado obj) {
        return null;
    }

    public GenericResponse delete(Long aLong) {
        return null;
    }

}
