package com.example.backendapimbanking.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto>uploadMultiple(List<MultipartFile> files);
    List<FileDto>getAllFiles();
    FileDto deleteSingleFile(String fileName);
    boolean removeAllFile();
    FileDto downLoadFiles(String filePaht) throws IOException;
}
