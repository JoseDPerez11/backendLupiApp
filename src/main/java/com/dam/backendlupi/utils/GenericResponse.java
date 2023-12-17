package com.dam.backendlupi.utils;

import lombok.*;

@Getter
@Setter
public class GenericResponse<T> {

    private String type;
    private int rpta;
    private String message;
    private T body;

    public GenericResponse() {
        type = "";
        rpta = 0;
        message = "";
        body = null;
    }

    public GenericResponse(String bodyType, Object body) {
        type = "";
        rpta = 0;
        message = "";
        this.body = null;
    }

    public GenericResponse(String type, int rpta, String message, T body) {
        this.type = type;
        this.rpta = rpta;
        this.message = message;
        this.body = body;
    }

}
