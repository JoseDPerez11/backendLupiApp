package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.recomendado IS 1")
    Iterable<Producto> listarProductosRecomendados();

    @Query("SELECT p FROM Producto p WHERE p.categoria.id=:idC")
    Iterable<Producto> listarProductoPorCategoria(int idC);

    @Modifying
    @Query("UPDATE Producto p SET p.stock=stock-:cant WHERE p.id=:id")
    void actualizarStock(int cant, int id);
}
