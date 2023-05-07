package com.example.backendapimbanking.base;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record BaseError<T>(
        Boolean status,
        Integer code,
        String messages,
        LocalDateTime timeStamp,
        T errors
) {
}
