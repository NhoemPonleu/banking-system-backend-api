package com.example.backendapimbanking.util;

import com.example.backendapimbanking.api.file.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class FileUtil {
    @Value("${file.server-path}")
    public String fileServerPath;

    @Value("${file.base-url}")
    public String fileBaseUrl;
    @Value("${file.base-download}")
    public String fileDownload;
    private Path foundFile;
    public Resource resource;

    public FileDto upload(MultipartFile file){
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex);
        long size = file.getSize();
        String name = String.format("%s%s", UUID.randomUUID(),extension);
        String url = String.format("%s%s",fileBaseUrl,name);
        String downloadUrl=fileDownload+name;
        Path path = Paths.get(fileServerPath + name);

        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(name)
                    .url(url)
                    .extension(extension)
                    .downloadUrl(downloadUrl)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload file failed");
        }

    }
    public List<FileDto>uploadMutiple(List<MultipartFile> multipartFiles){
        List<FileDto>list=new ArrayList<>();
        for(MultipartFile file:multipartFiles){
            int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
            String extension = file.getOriginalFilename().substring(lastDotIndex);
            long size = file.getSize();
            String name = String.format("%s%s", UUID.randomUUID(),extension);
            String url = String.format("%s%s",fileBaseUrl,name);
            Path path = Paths.get(fileServerPath + name);

            try {
                Files.copy(file.getInputStream(), path);
                 list.add(FileDto.builder()
                        .name(name)
                        .url(url)
                        .extension(extension)
                        .size(size)
                        .build());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Upload file failed");
            }
        }
        return list;

    }
    public  Resource findByName(String name){
        Path path=Paths.get(fileServerPath+name);
        try {
            Resource resource1= new UrlResource(path.toUri());
            if(resource1.exists()){
                return resource1;
            }
            throw new ResponseStatusException((HttpStatus.INTERNAL_SERVER_ERROR),"File Not Found");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteByName(String name){
        Path path=Paths.get(fileServerPath+name);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new  ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"File is failed Deleted");
        }
    }
}

