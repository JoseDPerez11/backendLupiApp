package com.dsw.backendlupi.service;

import com.dsw.backendlupi.entity.DocumentoAlmacenado;
import com.dsw.backendlupi.repository.DocumentoAlmacenadoRepository;
import com.dsw.backendlupi.utils.GenericResponse;
import org.springframework.core.io.Resource;
import static com.dsw.backendlupi.utils.Global.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class DocumentoAlmacenadoService {

    @Autowired
    private DocumentoAlmacenadoRepository daRepository;
    @Autowired
    private FileStorageService storageService;

    // Listar imagenes
    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, daRepository.list());
    }

    // Guardar archivo
    public GenericResponse save(DocumentoAlmacenado documentoAlmacenado) {
        // Obtiene el nombre de archivo actual asociado al documento almacenado, o un nombre vacío si no existe.
        String fileName = daRepository.findById(documentoAlmacenado.getId()).orElse(new DocumentoAlmacenado()).getFileName();

        // Obtiene el nombre original del archivo adjunto al documento almacenado
        String originalFilename = documentoAlmacenado.getFile().getOriginalFilename();
        // Extrae la extensión del archivo a partir del nombre original.
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Almacena el archivo y obtiene el nombre actualizado del archivo almacenado.
        fileName = storageService.storeFile(documentoAlmacenado.getFile(), fileName);

        // Actualiza el nombre del archivo y la extensión en el objeto documentoAlmacenado.
        documentoAlmacenado.setFileName(fileName);
        documentoAlmacenado.setExtension(extension);

        // Retorna una respuesta genérica con información sobre la operación realizada.
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, daRepository.save(documentoAlmacenado));
    }

    // Generar una respuesta (ResponseEntity) que permitirá la descarga de un archivo
    public ResponseEntity<Resource> download(String completeFilename, HttpServletRequest request) {
        // Carga el recurso (archivo) utilizando el servicio de almacenamiento.
        Resource resource = storageService.loadResource(completeFilename);
        String contentType = null;

        // Obtiene el tipo de contenido (MIME type) del archivo.
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si no se puede determinar el tipo de contenido, se establece como "application/octet-stream".
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Construye y retorna una respuesta ResponseEntity para la descarga del archivo.
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders
                        .CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Proporciona una capa adicional que permite descargar un archivo basado en el nombre de archivo (fileName).
    // Primero, busca el documento almacenado asociado al nombre de archivo y luego utiliza el método download para
    // generar la respuesta de descarga.
    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = daRepository.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

}
