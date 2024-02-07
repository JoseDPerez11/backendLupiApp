package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.recomendado IS 1")
    Iterable<Producto> listarProductosRecomendados();

    @Query("SELECT p FROM Producto p WHERE p.categoria.id=:idC")
    Iterable<Producto> listarProductoPorCategoria(int idC);
}
