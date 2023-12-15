package com.dam.backendlupi.services;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import com.dam.backendlupi.repository.DocumentoAlmacenadoRepository;
import com.dam.backendlupi.utils.GenericResponse;
import com.dam.backendlupi.utils.Global;
import static com.dam.backendlupi.utils.Global.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DocumentoAlmacenadoService {

    @Autowired
    private DocumentoAlmacenadoRepository repo;
    @Autowired
    private FileStorageService storageService;

    //listar imagenes
    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

}
