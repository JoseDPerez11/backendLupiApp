package com.dsw.backendlupi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta clase es una configuración de Spring.
@EnableWebMvc // Habilita la configuración específica de MVC en la aplicación.
public class WebConfig implements WebMvcConfigurer {

    // Método para configurar políticas de CORS.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Se permite el acceso desde cualquier origen.
        registry.addMapping("/**")
                .allowedOrigins("*")
                // Se permiten los siguientes métodos HTTP.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                // Permite incluir credenciales en las solicitudes (como cookies o encabezados de autorización).
                .allowCredentials(true);
    }
}
