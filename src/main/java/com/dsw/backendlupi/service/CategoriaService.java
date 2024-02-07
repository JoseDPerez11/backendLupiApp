package com.dsw.backendlupi.service;

import com.dsw.backendlupi.repository.CategoriaRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public GenericResponse listarCategoriasActivas() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, categoriaRepository.listarCategoriasActivas());
    }

}
