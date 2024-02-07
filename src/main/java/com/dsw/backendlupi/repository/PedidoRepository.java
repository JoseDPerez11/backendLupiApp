package com.dsw.backendlupi.repository;

import com.dsw.backendlupi.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id=:idCli")
    Iterable<Pedido> devolverMisCompras(int idCli);
}
