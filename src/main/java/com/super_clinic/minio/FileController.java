package com.super_clinic.minio;

import io.minio.errors.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/storage")
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String bucketName = "bucket"; // Укажите имя бакета
            String objectName = file.getOriginalFilename();

            storageService.uploadFile(bucketName, objectName, file.getInputStream(), file.getContentType());
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/download/**")
    public void downloadFile(HttpServletRequest request) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String requestURL = request.getRequestURL().toString();

        //System.out.println(requestURL);
        String body = requestURL.split("/download/")[1];

        System.out.println(body);

        storageService.downloadFile(body);
    }

}
