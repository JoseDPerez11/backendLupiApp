package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.Cliente;
import com.dsw.backendlupi.repository.ClienteRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import com.dsw.backendlupi.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.dsw.backendlupi.utils.Global.*;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository cRepository;

    // Guardar y/o actualizar cliente
    public GenericResponse save(Cliente cliente) {
        // Buscar el cliente por ID
        Optional<Cliente> optionalCliente = cRepository.findById(cliente.getId());
        int idf = optionalCliente.isPresent() ? optionalCliente.get().getId() : 0;

        // Si el ID es 0, significa que es un nuevo cliente
        if (idf == 0) {
            // Verificar si ya existe un cliente con el mismo número de documento
            if (cRepository.existByDoc(cliente.getNumDoc().trim()) == 1) {
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING,
                        "Lo sentimos, ya existe un cliente con ese documento", null);
            } else {
                // Guardar el nuevo cliente
                cliente.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK,
                        "Cliente registrado correctamente", this.cRepository.save(cliente));
            }
        } else {
            // Si el ID no es 0, significa que es una actualización de cliente existente
            // Verificar si ya existe otro cliente con los mismos datos (excluyendo el cliente actual)
            if (cRepository.existByDocForUpdate(cliente.getNumDoc().trim(), cliente.getId()) == 1) {
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING,
                        "Ya existe un cliente con los mismos datos, verifique e intente de nuevo", null);
            } else {
                // Actualizar los datos del cliente
                cliente.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK,
                        "Datos del cliente actualizado", this.cRepository.save(cliente));
            }
        }
    }

}
