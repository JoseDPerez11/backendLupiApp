package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.entity.DocumentoAlmacenado;
import com.dsw.backendlupi.service.DocumentoAlmacenadoService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/documento-almacenado")
public class DocumentoAlmacenadoController {

    @Autowired
    private DocumentoAlmacenadoService daService;

    @GetMapping
    public GenericResponse list() {
        return daService.list();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return daService.downloadByFileName(fileName, request);
    }

    @PostMapping
    public GenericResponse save(@ModelAttribute DocumentoAlmacenado documentoAlmacenado) {
        return daService.save(documentoAlmacenado);
    }



}
