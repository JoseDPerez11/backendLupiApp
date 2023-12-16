package com.dam.backendlupi.exception;


import com.dam.backendlupi.utils.GenericResponse;
import com.dam.backendlupi.utils.Global;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//mostrar una operación errónea en caso de que no se pueda subir la imagen
//restcontrolleradvice: proporciona manejo global de excepciones para todos los controladores en la aplicación
@RestControllerAdvice
public class GenericExceptionHandler {

    //significa que ese método manejará excepciones de tipo Exception en toda la aplicación,
    // proporcionando una lógica de manejo de errores global y coherente. Es útil para manejar
    // errores inesperados o no específicos en tu aplicación.
    @ExceptionHandler(Exception.class)
    public GenericResponse genericException(Exception ex) {
        return new GenericResponse("exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}
