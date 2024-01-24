package com.dsw.backendlupi.exception;

//excepcion para el archivo no encontrado
public class MyFileNotFoundException extends RuntimeException {

    public MyFileNotFoundException(String message) {
        super(message);
    }
    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
