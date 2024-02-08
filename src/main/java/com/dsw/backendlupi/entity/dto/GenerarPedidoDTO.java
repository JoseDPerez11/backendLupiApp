package com.dsw.backendlupi.entity.dto;

import com.dsw.backendlupi.entity.Cliente;
import com.dsw.backendlupi.entity.DetallePedido;
import com.dsw.backendlupi.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class GenerarPedidoDTO {
    private Pedido pedido;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Iterable<DetallePedido> detallePedidos;
    private Cliente cliente;
}
