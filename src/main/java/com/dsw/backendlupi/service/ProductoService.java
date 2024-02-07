package com.dsw.backendlupi.service;

import com.dsw.backendlupi.repository.ProductoRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import com.dsw.backendlupi.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public GenericResponse listarProductosRecomendados() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, productoRepository.listarProductosRecomendados());
    }

    public GenericResponse listarProductosPorCategoria(int idC) {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, productoRepository.listarProductoPorCategoria(idC));
    }
}
