package com.dsw.backendlupi.service;

import com.dsw.backendlupi.config.FileStorageProperties;
import com.dsw.backendlupi.exception.FileStorageException;
import com.dsw.backendlupi.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private FileStorageProperties fileStorageProperties;

    // Devuelve el nombre del archivo almacenado.
    public String storeFile(MultipartFile file, String fileName) {
        // Limpia el nombre original del archivo de cualquier ruta maliciosa o caracteres especiales
        String originalName = StringUtils.cleanPath(file.getOriginalFilename());

        // Extrae la extensión del archivo a partir del nombre original
        String extension = originalName.substring(originalName.lastIndexOf("."));

        // Si el nombre del archivo proporcionado está vacío, genera un nombre único utilizando UUID
        if (fileName.isEmpty()) {
            fileName = UUID.randomUUID().toString();
        }

        // Obtiene la ubicación del almacenamiento de archivos basada en el tipo de archivo
        Path fileStorageLocation = getFileStorageLocation(getFolderName(originalName));
        // Resuelve la ubicación del archivo de destino incluyendo el nombre del archivo y la extensión
        Path targetLocation = fileStorageLocation.resolve(fileName + extension);

        try {
            // Copia el contenido del archivo de entrada al archivo de destino
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException e) {
            throw new FileStorageException("No se pudo almacenar el archivo", e);
        }
    }

    // Método para obtener el nombre de la carpeta
    private String getFolderName(String completeFileName) {
        // Extrae la extensión del archivo
        String extension = completeFileName.substring(completeFileName.lastIndexOf("."));
        // Elimina puntos y convierte a mayúsculas
        return extension.replace(".", "").toUpperCase();
    }

    // Método para obtener la ubicación del almacenamiento de archivos
    private Path getFileStorageLocation(String folderName) {
        // Obtener la ubicación del almacenamiento de archivos a partir de la propiedad de configuración
        Path fileStorageLocation = Paths.get(fileStorageProperties
                .getUploadDir() + "/" + folderName)
                .toAbsolutePath().normalize();
        try {
            // Crear directorios si no existen
            Files.createDirectories(fileStorageLocation);
            return fileStorageLocation;
        } catch (IOException e) {
            throw new FileStorageException("No se pudo crear el directorio", e);
        }
    }

    public Resource loadResource(String completeFileName) {
        Path fileStorageLocation = getFileStorageLocation(getFolderName(completeFileName));
        Path path = fileStorageLocation.resolve(completeFileName).normalize();

        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Archivo no encontrado " + completeFileName);
            }
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Ha ocurrido un error al intentar acceder al archivo: " + completeFileName, e);
        }
    }

}
