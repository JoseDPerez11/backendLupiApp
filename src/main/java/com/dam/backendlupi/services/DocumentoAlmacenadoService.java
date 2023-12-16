package com.dam.backendlupi.services;

import com.dam.backendlupi.entity.DocumentoAlmacenado;
import com.dam.backendlupi.repository.DocumentoAlmacenadoRepository;
import com.dam.backendlupi.utils.GenericResponse;

import static com.dam.backendlupi.utils.Global.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DocumentoAlmacenadoService {

    private DocumentoAlmacenadoRepository docAlmacenadoRepo;
    private FileStorageService storageService;

    //listar imagenes
    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, docAlmacenadoRepo.list());
    }

    public GenericResponse find(Long aLong) { return null ;}

    //guardar un archivo
    public GenericResponse save(DocumentoAlmacenado obj) {
        String fileName = (docAlmacenadoRepo.findById(obj.getId())).orElse(new DocumentoAlmacenado()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA,docAlmacenadoRepo.save(obj));
    }

    //descargar un archivo
    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = docAlmacenadoRepo.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

    //eliminar pero está null
    public GenericResponse delete(Long aLong) {
        return null;
    }

    //Validar
    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }

}
