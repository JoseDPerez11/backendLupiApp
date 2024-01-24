package com.dsw.backendlupi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

// @ConfigurationProperties indica que esta clase contendrá propiedades de configuración,
// y el prefijo "file" se utiliza para agrupar las propiedades que comienzan con "file" en esta clase.
@ConfigurationProperties(prefix = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FileStorageProperties {

    private String uploadDir;
}
