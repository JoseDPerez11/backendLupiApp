package com.dam.backendlupi.exception;


import com.dam.backendlupi.utils.GenericResponse;
import com.dam.backendlupi.utils.Global;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//mostrar una operación errónea en caso de que no se pueda subir la imagen
@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(Exception.class)
    public GenericResponse genericException(Exception ex) {
        return new GenericResponse("exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}
