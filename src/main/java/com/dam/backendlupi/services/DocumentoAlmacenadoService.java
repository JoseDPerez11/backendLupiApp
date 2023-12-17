package com.dam.backendlupi.services;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import com.dam.backendlupi.repository.DocumentoAlmacenadoRepository;

import static com.dam.backendlupi.utils.Global.*;

import com.dam.backendlupi.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;



@Service
@Transactional
public class DocumentoAlmacenadoService {

    @Autowired
    private DocumentoAlmacenadoRepository docAlmacenadoRepo;
    @Autowired
    private FileStorageService storageService;


    // esta función devuelve un objeto GenericResponse que envuelve una lista de DocumentoAlmacenado.
    // El GenericResponse proporciona información sobre el tipo de resultado, el código de
    // respuesta, y un mensaje descriptivo.
    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, docAlmacenadoRepo.list());
    }


    //guardar un objeto DocumentoAlmacenado
    public GenericResponse save(DocumentoAlmacenado obj) {

        // Obtiene el nombre de archivo actual del objeto DocumentoAlmacenado almacenado en la
        // base de datos.
        // Si el objeto no existe (por ejemplo, en el caso de un nuevo objeto sin ID), utiliza
        // un objeto DocumentoAlmacenado vacío y obtiene el nombre de archivo de ese objeto.
        String fileName = (docAlmacenadoRepo.findById(obj.getId())).orElse(new DocumentoAlmacenado()).getFileName();

        // Obtiene el nombre original del archivo enviado en el objeto DocumentoAlmacenado.
        String originalFilename = obj.getFile().getOriginalFilename();
        // Obtiene la extensión del archivo a partir del nombre original.
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Almacena o actualiza el archivo en el sistema de archivos utilizando el servicio
        // storageService. Este método probablemente utiliza el nombre actual del archivo (obtenido en el paso 1)
        // y el nuevo archivo enviado en el objeto DocumentoAlmacenado. Devuelve el nuevo
        // nombre del archivo, que se asigna a la variable fileName.
        fileName = storageService.storeFile(obj.getFile(), fileName);

        // Establece el nombre del archivo y la extensión en el objeto DocumentoAlmacenado
        // antes de guardarlo en la base de datos.
        obj.setFileName(fileName);
        obj.setExtension(extension);

        // Guarda el objeto DocumentoAlmacenado actualizado en la bd y devuelve la respuesta
        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA, docAlmacenadoRepo.save(obj));
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = docAlmacenadoRepo.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }


    //manejar la descarga de un archivo identificado por su nombre completo (completefileName).
    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {

        // Obtiene el recurso (archivo) desde el servicio de almacenamiento
        Resource resource = storageService.loadResource(completefileName);

        // Obtiene el tipo de contenido (MIME type) del archivo a través del contexto del servlet
        // (ServletContext). Esto se hace utilizando la ruta absoluta del archivo. Si no
        // puede determinar el tipo de contenido, se establecerá como null.
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si no se puede determinar el tipo de contenido, se establece como "application/octet-stream",
        // que es un tipo de contenido genérico para archivos binarios.
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Construye y devuelve una respuesta HTTP para la descarga del archivo
        // Construye y devuelve una respuesta HTTP (ResponseEntity) con un código de estado "200 OK".
        return ResponseEntity.ok()
                // establecer el encabezado Content-Type de la respuesta.
                .contentType(MediaType.parseMediaType(contentType))
                // indicar que el contenido debe ser tratado como un archivo adjunto (attachment) y especifica
                // el nombre de// archivo que se mostrará al usuario.
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                // El cuerpo de la respuesta contiene el recurso (resource), que es el archivo que se enviará en la respuesta.
                .body(resource);
    }

    public GenericResponse find(Long aLong) { return null ;}

    //eliminar pero está null
    public GenericResponse delete(Long aLong) {
        return null;
    }

    //Validar
    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }

}
