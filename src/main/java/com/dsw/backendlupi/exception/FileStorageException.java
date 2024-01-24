package com.dsw.backendlupi.exception;

//excepcion para un problema de imagen
public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
