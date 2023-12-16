package com.dam.backendlupi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DocumentoAlmacenado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String fileName;
    private String extension;
    private String estado;
    private boolean eliminado;

    @Transient //le dice al proveedor de persistencia que ignore ese campo al realizar operaciones de persistencia, como insertar o actualizar registros en la base de datos.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //@JsonProperty es parte de la biblioteca Jackson, que se utiliza para serializar y deserializar objetos JSON en Java.
    //.Access.WRITE_ONLY indica que la propiedad solo debe ser considerada al serializar el objeto a formato JSON y no debe ser considerada durante la deserialización desde JSON.
    private MultipartFile file; //se utiliza para manejar archivos cargados en el lado del servidor

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
