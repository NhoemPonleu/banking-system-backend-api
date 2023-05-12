package com.example.backendapimbanking.api.file;

import com.example.backendapimbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    public String fileServerPath;

    @Value("${file.base-url}")
    public String fileBaseUrl;
    @Value("${file.base-download}")
    public String fileDownload;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {

        return fileUtil.upload(file);

    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        return fileUtil.uploadMutiple(files);
    }

    @Override
    public List<FileDto> getAllFiles() {
        List<FileDto> listFile = new ArrayList<>();
        File file = new File(fileUtil.fileServerPath);
        File[] listOfFiles = file.listFiles();

        for (File files : listOfFiles) {
            if (files.isFile()) {
                String name = files.getName();
                String url = String.format("%s%s", fileUtil.fileBaseUrl, name);
                long size = file.length();
                int lastDotIndex = name.lastIndexOf(".");
                String downloadUrl =fileDownload + name;
                String extension = name.substring(lastDotIndex + 1);

                listFile.add(new FileDto(name, url, downloadUrl,extension, size));
            }
        }
        return listFile;
    }

    @Override
    public FileDto deleteSingleFile(String fileName) {
        FileDto fileDto = this.getAllFiles().stream().filter(
                f -> f.name().equalsIgnoreCase(fileName)).findFirst().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Files Not Found"));

        File file = new File(fileUtil.fileServerPath, fileName);
        file.delete();
        return fileDto;

    }

    @Override
    public boolean removeAllFile() {
        if (this.getAllFiles().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File is Empty !");
        }

        for (FileDto file : this.getAllFiles()) {
            this.deleteSingleFile(file.name());
        }

        return true;
    }


    @Override
    public Resource downloadFile(String fileName) {
        Resource resource=new PathResource(fileServerPath+fileName);
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException(" File not found");
        }

        return resource;
    }

    @Override
    public FileDto fndByName(String name) {

        return null;
    }

    @Override
    public void deleteByName(String name) {
     fileUtil.deleteByName(name);
    }

    @Override
    public Resource downloadFileName(String name) {

        return  fileUtil.findByName(name);
    }

}