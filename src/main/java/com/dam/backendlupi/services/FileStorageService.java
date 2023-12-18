package com.dam.backendlupi.services;

import com.dam.backendlupi.config.FileStorageProperties;
import com.dam.backendlupi.exception.FileStorageException;
import com.dam.backendlupi.exception.MyFileNotFoundException;
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


    public String storeFile(MultipartFile file, String fileName) {
        // Obtiene el nombre original del archivo, limpia el path para asegurar que no haya
        // caracteres no deseados.
        String originalName = StringUtils.cleanPath(file.getOriginalFilename());

        // Obtiene la extensión del archivo. Esto se utiliza para construir el nombre del
        // archivo almacenado.
        String extension = originalName.substring(originalName.lastIndexOf("."));

        // Si el nombre del archivo está vacío, genera un nombre único utilizando UUID. Esto
        // es útil si el cliente no proporciona un nombre de archivo específico.
        if (fileName.equals("")) {
            fileName = UUID.randomUUID().toString();
        }

        // Obtiene la ubicación del almacenamiento del archivo. Esto podría ser una ruta en
        // el sistema de archivos donde se guardarán los archivos.
        Path fileStorageLocation = getFileStorageLocation(getFolderName(originalName));

        // Resuelve la ubicación objetivo utilizando el nombre del archivo y la extensión.
        // Esta es la ubicación donde se almacenará el archivo.
        Path targetLocation = fileStorageLocation.resolve(fileName + extension);

        try {
            // Copia los bytes del archivo desde el InputStream del objeto MultipartFile al destino especificado.
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            // Devuelve el nombre del archivo almacenado. Este valor puede ser útil para informar al cliente sobre
            // el nombre real del archivo almacenado.
            return fileName;

        } catch (IOException e) {
            // Captura y relanza una excepción específica si hay un error
            throw new FileStorageException("No se pudo almacenar el archivo", e);
        }
    }

    // carga un recurso (Resource) correspondiente a un archivo en función de su nombre completo.
    public Resource loadResource(String completeFileName) {
        // Obtiene la ubicación del almacenamiento de archivos
        Path fileStorageLocation = getFileStorageLocation(getFolderName(completeFileName));

        // Resuelve la ruta completa del archivo concatenando la ubicación del almacenamiento de
        // archivos con el nombre del archivo y normalizando la ruta.
        Path path = fileStorageLocation.resolve(completeFileName).normalize();
        try {
            // Crea un objeto Resource a partir de la URL de la ruta del archivo. La implementación
            // concreta utilizada aquí es UrlResource.
            Resource resource = new UrlResource(path.toUri());

            // Verifica si el recurso existe
            if (resource.exists()) {
                return resource;
            } else {
                // Lanza una excepción si el recurso no existe
                throw new MyFileNotFoundException("Archivo no encontrado: " + completeFileName);
            }

        } catch (MalformedURLException e) {
            // Lanza una excepción si hay un error de URL al acceder al archivo
            throw new MyFileNotFoundException("Ha ocurrido un error al intentar acceder al archivo: " + completeFileName, e);
        }
    }


    //toma el nombre completo de un archivo (incluyendo la extensión) y devuelve un
    //nombre de carpeta basado en la extensión del archivo.
    private String getFolderName(String completeFileName) {
        // Obtiene la extensión del archivo (incluyendo el punto)
        String extension = completeFileName.substring(completeFileName.lastIndexOf("."));

        // Elimina el punto de la extensión y convierte a mayúsculas
        return extension.replace(".", "").toUpperCase();
    }

    //diseñada para obtener la ubicación de almacenamiento de archivos en función del
    //nombre de la carpeta proporcionado.
    private Path getFileStorageLocation(String folderName) {
        // obtiene la ruta base del almacenamiento de archivos desde las propiedades de configuración.
        // .toAbsolutePath() convierte la ruta a una ruta absoluta.
        // .normalize() normaliza la ruta para manejar cualquier ".." o referencias de directorios redundantes.
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + "/" + folderName).toAbsolutePath().normalize();
        try {
            // Crea directorios si no existen
            Files.createDirectories(fileStorageLocation);

            // Devuelve la ubicación del almacenamiento de archivos después de crear los directorios necesarios.
            return fileStorageLocation;
        } catch (IOException e) {
            throw new FileStorageException("No se pudo crear el directorio", e);
        }
    }
}
