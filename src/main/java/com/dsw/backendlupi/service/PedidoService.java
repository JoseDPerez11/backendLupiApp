package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.Pedido;
import com.dsw.backendlupi.entity.dto.PedidoConDetallesDTO;
import com.dsw.backendlupi.repository.DetallePedidoRepository;
import com.dsw.backendlupi.repository.PedidoRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisCompras(int idCli) {
        final List<PedidoConDetallesDTO> dtos = new ArrayList<>();
        final Iterable<Pedido> pedidos = pedidoRepository.devolverMisCompras(idCli);

        pedidos.forEach(p -> {
            dtos.add(new PedidoConDetallesDTO(p, detallePedidoRepository.findByPedido(p.getId())));
        });
        return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Peticion encontrada", dtos);
    }

}
