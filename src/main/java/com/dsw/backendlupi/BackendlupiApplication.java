package com.dsw.backendlupi;

import com.dsw.backendlupi.exception.FileStorageException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class BackendlupiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendlupiApplication.class, args);
	}

}
