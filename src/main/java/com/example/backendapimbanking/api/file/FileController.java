package com.example.backendapimbanking.api.file;

import com.example.backendapimbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @Value("${file.base-url}")
    public String fileBaseUrl;

    @PostMapping
    public BaseRest<?> filesUpload(@RequestPart("file") MultipartFile multipartFile) {
        FileDto fileDto = fileService.uploadSingle(multipartFile);
        return BaseRest.builder()
                .status(true)
                .messages("File has been upload")
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .data(fileDto)
                .build();
    }
    @GetMapping("/download1/name")
    public ResponseEntity<?>downloadFiles(@PathVariable  String name){
          Resource resource=fileService.downloadFileName(name);
        return  null;//ResponseEntity.ok();
             //   .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM,"" ));

    }

    @PostMapping("/multiple")
    public BaseRest<?> multipleFilesUpload(@RequestPart("file") List<MultipartFile> multipartFile) {
        List<FileDto> fileDto = fileService.uploadMultiple(multipartFile);
        return BaseRest.builder()
                .status(true)
                .messages("File has been upload")
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .data(fileDto)
                .build();
    }

    @DeleteMapping("/{fileName}")
    public BaseRest<?> deleteFiles(@PathVariable String fileName) {
        FileDto fileDto = fileService.deleteSingleFile(fileName);
        return BaseRest.builder()
                .status(true)
                .messages("File Has been Deleted")
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .data(fileDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> getAllFiles() {
        List<FileDto> fileDtos = fileService.getAllFiles();
        if (fileDtos.isEmpty()) {
            return BaseRest
                    .builder()
                    .data(null)
                    .messages("Data Not have Please Enter Data to get")
                    .build();
        } else {
            return BaseRest.builder().status(true)
                    .code(HttpStatus.OK.value())
                    .messages("File have been get")
                    .timeStamp(LocalDateTime.now())
                    .data(fileDtos)
                    .build();
        }

    }

    @DeleteMapping
    public BaseRest<?> removeAllFile() {
        boolean files = fileService.removeAllFile();
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .messages("File have been Removed ")
                .timeStamp(LocalDateTime.now())
                .data(files)
                .build();
    }
    @GetMapping("/downloads/{fileName}")
    public BaseRest<?> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = fileService.downloadFile(fileName);
        System.out.println(fileBaseUrl+"api/v1/files/download/"+fileName);
        return BaseRest.builder().build();
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"")
//                .body(resource);


    }
    @DeleteMapping("deleted/{name}")
    public void deleteFileByName(@PathVariable String name){
        fileService.deleteByName(name);
    }
}
