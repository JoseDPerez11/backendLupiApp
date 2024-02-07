package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.DetallePedido;
import com.dsw.backendlupi.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public void guardarDetalles(Iterable<DetallePedido> detallePedido) {
        detallePedidoRepository.saveAll(detallePedido);
    }

}
