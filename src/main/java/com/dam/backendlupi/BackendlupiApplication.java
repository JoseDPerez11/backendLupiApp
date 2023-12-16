package com.dam.backendlupi;

import com.dam.backendlupi.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class BackendlupiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendlupiApplication.class, args);
	}

}
