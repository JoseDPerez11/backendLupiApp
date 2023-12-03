package com.dam.backendlupi.repository;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DocumentoAlmacenadoRepository extends CrudRepository<DocumentoAlmacenado, Long> {
    @Query("SELECT da FROM DocumentoAlmacenado da WHERE da.estado = 'A' AND da.eliminado = false")
    Iterable<DocumentoAlmacenado> list();
    @Query("SELECT da FROM DocumentoAlmacenado da WHERE da.fileName =:fileName AND da.estado = 'A' AND da.eliminado = false")
    Iterable<DocumentoAlmacenado> findByFileName(String fileName);

}
