package com.dam.backendlupi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* esta clase de configuracion está habilitando y personalizando la configuración de
* CORS(Cross-Origin Resource Sharing) para permitir el acceso a los recursos desde
* cualquier origen, con ciertos métodos HTTP permitidos y la capacidad de incluir
* credenciales en las solicitudes CORS. */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
