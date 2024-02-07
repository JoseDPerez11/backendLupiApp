package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {

    @Query("SELECT dp FROM DetallePedido dp WHERE dp.pedido.id=:idP")
    Iterable<DetallePedido> findByPedido(int idP);
}
