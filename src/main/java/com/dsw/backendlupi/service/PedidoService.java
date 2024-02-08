package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.DetallePedido;
import com.dsw.backendlupi.entity.Pedido;
import com.dsw.backendlupi.entity.dto.GenerarPedidoDTO;
import com.dsw.backendlupi.entity.dto.PedidoConDetallesDTO;
import com.dsw.backendlupi.repository.DetallePedidoRepository;
import com.dsw.backendlupi.repository.PedidoRepository;
import com.dsw.backendlupi.repository.ProductoRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private DetallePedidoService detallePedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public GenericResponse<List<PedidoConDetallesDTO>> devolverMisCompras(int idCli) {
        final List<PedidoConDetallesDTO> dtos = new ArrayList<>();
        final Iterable<Pedido> pedidos = pedidoRepository.devolverMisCompras(idCli);

        pedidos.forEach(p -> {
            dtos.add(new PedidoConDetallesDTO(p, detallePedidoRepository.findByPedido(p.getId())));
        });
        return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Peticion encontrada", dtos);
    }

    public GenericResponse guardarPedido(GenerarPedidoDTO pedidoDto) {
        java.util.Date date = new java.util.Date();
        pedidoDto.getPedido().setFechaCompra(new java.sql.Date(date.getTime()));
        pedidoDto.getPedido().setAnularPedido(false);
        pedidoDto.getPedido().setMonto(pedidoDto.getPedido().getMonto());
        pedidoDto.getPedido().setCliente(pedidoDto.getCliente());
        pedidoRepository.save(pedidoDto.getPedido());

        for (DetallePedido detallePedido: pedidoDto.getDetallePedidos()) {
            detallePedido.setPedido(pedidoDto.getPedido());
            productoRepository.actualizarStock(detallePedido.getCantidad(), detallePedido.getProducto().getId());
        }

        detallePedidoService.guardarDetalles(pedidoDto.getDetallePedidos());

        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, pedidoDto);
    }

    public GenericResponse anularPedido(int id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(new Pedido());

        if (pedido.getId() != 0) {
            pedido.setAnularPedido(true);
            pedidoRepository.save(pedido);
            return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, pedido);
        } else {
            return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_ERRONEA, "El pedido que desea anular no es válido");
        }
    }

}
