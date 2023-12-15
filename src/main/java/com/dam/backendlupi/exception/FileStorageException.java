package com.dam.backendlupi.exception;

import com.dam.backendlupi.config.FileStorageProperties;

//excepcion para un problema de imagen
public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
