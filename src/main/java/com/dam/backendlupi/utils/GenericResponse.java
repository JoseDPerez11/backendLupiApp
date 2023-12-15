package com.dam.backendlupi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private String type;
    private int rpta;
    private String message;
    private T body;

}
