package com.example.backendapimbanking.base;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record BaseRest<T>(Boolean status,
                       Integer code,
                       String messages,
                       LocalDateTime timeStamp,
                       T data
                       ) {
}
