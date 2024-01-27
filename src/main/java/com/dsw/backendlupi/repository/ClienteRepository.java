package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    // Comprueba la existencia de un cliente por número de documento
    @Query(value = "SELECT EXISTS(SELECT id FROM cliente WHERE num_doc=:dni)", nativeQuery = true)
    int existByDoc(String dni);

    // Comprueba la existencia de un cliente por número de documento excluyendo un ID específico
    @Query(value = "SELECT EXISTS(SELECT c.* FROM cliente c WHERE c.num_doc=:dni AND NOT (c.id=:id)", nativeQuery = true)
    int existByDocForUpdate(String dni, int id);

}
