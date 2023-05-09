package com.example.backendapimbanking.exception;

import com.example.backendapimbanking.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?>handleServieException(ResponseStatusException e){

        return BaseError.builder()
                .errors(e.getReason()).

                status(false).
                messages("Something went wrong")
                .code(e.getStatusCode().value())
                .timeStamp(LocalDateTime.now()).

                build();

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException ex){
        List<Map<String,String>>er=new ArrayList<>();

        for(FieldError err:ex.getFieldErrors()){
            Map<String,String>errorsDetails=new HashMap<>();
            errorsDetails.put("name",err.getField());
            errorsDetails.put("message",err.getDefaultMessage());
            er.add(errorsDetails);

        }
        return BaseError.builder()
                        .errors(er)
                        .status(false)
                        .messages("validation erro")
                        .code(HttpStatus.BAD_REQUEST.value())
                        .timeStamp(LocalDateTime.now())
                        .build();

    }
}
