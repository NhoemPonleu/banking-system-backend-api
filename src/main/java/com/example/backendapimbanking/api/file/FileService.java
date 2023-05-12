package com.example.backendapimbanking.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto>uploadMultiple(List<MultipartFile> files);
    List<FileDto>getAllFiles();
    FileDto deleteSingleFile(String fileName);
    boolean removeAllFile();
    Resource downloadFile(String fileName);
    FileDto fndByName(String name);
    void deleteByName(String name);
    Resource downloadFileName(String name);
}
