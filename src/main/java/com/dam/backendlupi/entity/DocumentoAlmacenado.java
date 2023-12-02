package com.dam.backendlupi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
public class DocumentoAlmacenado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String fileName;
    private String extension;
    private String estado;
    private boolean eliminado;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile file;
    @Transient
    private String urlFile;

    public DocumentoAlmacenado() {
        id = 0;
        nombre = "";
        fileName = "";
        extension = "";
        estado = "A";
        eliminado = false;
    }

    public String getCompleteFileName() {
        return fileName + extension;
    }
}
