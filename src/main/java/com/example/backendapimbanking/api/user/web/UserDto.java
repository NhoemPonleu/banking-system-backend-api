package com.example.backendapimbanking.api.user.web;

import lombok.Builder;
@Builder
public record UserDto(
        String name,
        String gender,
        String studentCardId,
        Boolean isStudent
) {
}
