package com.super_clinic.minio;

import com.super_clinic.entity.File;
import com.super_clinic.repository.FileRepository;
import com.super_clinic.service.impl.FileServiceImpl;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FileServiceImpl fileService;

    public void uploadFile(String bucketName, String objectName, InputStream inputStream, String contentType) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }

        File file = new File();
        file.setFilename(bucketName+"/"+objectName);
        fileService.save(file);
    }

    public void downloadFile(String url) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String[] parts = url.split("/", 2);
        String bucketName = parts[0];
        String fileName = parts[1];
        minioClient.downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .filename("my-object-file")
                        .build());

    }
}
