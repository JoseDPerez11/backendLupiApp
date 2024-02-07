package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c WHERE c.vigencia IS 1")
    Iterable<Categoria> listarCategoriasActivas();
}
