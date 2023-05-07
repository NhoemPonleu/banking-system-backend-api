package com.example.backendapimbanking.api.user.web;

import lombok.Builder;

@Builder
public record IsDeletedDto(boolean status) {
}
