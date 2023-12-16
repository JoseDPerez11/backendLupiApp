package com.dam.backendlupi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

//se utiliza para vincular propiedades de configuración en un archivo de propiedades o en otros orígenes de configuración a campos en una clase de configuración.
//prefix especifica el prefijo común que se utilizará para agrupar las propiedades en el archivo de configuración.
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageProperties {
    private String uploadDir;
}
