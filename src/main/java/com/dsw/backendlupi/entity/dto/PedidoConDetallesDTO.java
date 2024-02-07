package com.dsw.backendlupi.entity.dto;

import com.dsw.backendlupi.entity.DetallePedido;
import com.dsw.backendlupi.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
public class PedidoConDetallesDTO {
    private Pedido pedido;
    private Iterable<DetallePedido> detallePedidos;

    public PedidoConDetallesDTO() {
        this.pedido = new Pedido();
        this.detallePedidos = new ArrayList<>();
    }

}
