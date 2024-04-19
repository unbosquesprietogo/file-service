package co.edu.unbosque.fileservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileController {

    private static final String UPLOAD_DIR = "src/main/resources/static/files/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Archivo no seleccionado";
        }

        try {
            // Crea el directorio si no existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Guarda el archivo en el directorio de subida
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

            return "Archivo subido correctamente: " + filePath;
        } catch (IOException e) {
            return "Error al subir el archivo";
        }
    }
}
