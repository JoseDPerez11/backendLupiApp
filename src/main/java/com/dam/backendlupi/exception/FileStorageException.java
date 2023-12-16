package com.dam.backendlupi.exception;

public class FileStorageException extends RuntimeException{

    public FileStorageException(String message) {
        super(message);
    }

    //el tipo throwable es tipo excepcion, o sea que acepta una excepcion enviada desde la misma libreria
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
