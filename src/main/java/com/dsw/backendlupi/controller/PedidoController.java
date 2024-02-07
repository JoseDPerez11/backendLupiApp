package com.dsw.backendlupi.controller;

import com.dsw.backendlupi.entity.dto.PedidoConDetallesDTO;
import com.dsw.backendlupi.service.PedidoService;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/misPedidos({idCli}")
    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisComprasConDetalle(@PathVariable int idCli) {
        return pedidoService.devolverMisCompras(idCli);
    }

}
