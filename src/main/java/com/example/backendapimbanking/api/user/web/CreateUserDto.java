package com.example.backendapimbanking.api.user.web;

import lombok.Builder;

@Builder
public record CreateUserDto(
        String name,
        String gender,
        String studentCardId,
        Boolean isStudent,
        String oneSignalId


) {
}
