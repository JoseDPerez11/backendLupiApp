package com.dam.backendlupi.repository;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DocumentoAlmacenadoRepository  {

    //extends CrudRepository<DocumentoAlmacenado, Long>
    Iterable<DocumentoAlmacenado> list();
    Iterable<DocumentoAlmacenado> findByFileName(String fileName);

}
