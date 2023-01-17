package com.goormthon.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.goormthon.demo.common.BaseException;
import com.goormthon.demo.common.BaseExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    //단일 파일 올리기 (s3 파일 이름 반환)
    public String uploadFile(MultipartFile multipartFile, String dir) throws IOException {
        try {
            if(!checkFile(multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/")))) {
                throw new BaseException(BaseExceptionStatus.NOT_SUPPORT_FILE);
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            InputStream inputStream = multipartFile.getInputStream();
            objectMetadata.setContentLength(inputStream.available());
            String fileName = dir + "/" + UUID.randomUUID() +
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));

            return amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new BaseException(BaseExceptionStatus.FILE_UPLOAD_FAIL);
        }
    }

    //다중 파일 올리기 (s3 파일 이름 리스트 반환)
    public List<String> uploadFiles(List<MultipartFile> multipartFiles, String dir) throws IOException {
        List<String> fileNames = new ArrayList<>();

        for(MultipartFile multipartFile : multipartFiles) {
            fileNames.add(uploadFile(multipartFile, dir));
        }

        return fileNames;
    }

    public boolean checkFile(String contentType) {
        if(contentType.contains("png") || contentType.contains("jpeg")
                || contentType.contains("gif") || contentType.contains("bmp")
                || contentType.contains("pdf") || contentType.contains("mp4")
                || contentType.contains("mov") || contentType.contains("webm")
                || contentType.contains("ogg") || contentType.contains("wmv")
                || contentType.contains("avi") || contentType.contains("avchd")
                || contentType.contains("mpeg") || contentType.contains("mkv")) {
            return true;
        } else {
            return false;
        }
    }
}
