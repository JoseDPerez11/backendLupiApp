package com.dam.backendlupi.repository;

import com.dam.backendlupi.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    //metodo para no permitir permitir dni repetido
    @Query(value="(SELECT EXISTS(SELECT id FROM cliente WHERE num_doc =:dni))", nativeQuery = true)
    int existByDoc(String dni);

    //para actualizar dni, actualizamos dni y evaluamos que no sea igual al de otros clientes
    @Query(value="SELECT EXISTS(SELECT C.* FROM cliente C WHERE C.num_doc =:dni AND NOT (C.id =:id)", nativeQuery = true)
    int existDocUpdate(String dni, int id);
}
