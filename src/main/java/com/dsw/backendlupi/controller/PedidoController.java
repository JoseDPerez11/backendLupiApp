package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.entity.dto.GenerarPedidoDTO;
import com.dsw.backendlupi.entity.dto.PedidoConDetallesDTO;
import com.dsw.backendlupi.service.PedidoService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/misPedidos/{idCli}")
    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisComprasConDetalle(@PathVariable int idCli) {
        return pedidoService.devolverMisCompras(idCli);
    }

    @PostMapping
    public GenericResponse guardarPedido(@RequestBody GenerarPedidoDTO pedidoDto) {
        return pedidoService.guardarPedido(pedidoDto);
    }

    @DeleteMapping("/{id}")
    public GenericResponse anularPedido(@PathVariable int id) {
        return pedidoService.anularPedido(id);
    }

}
