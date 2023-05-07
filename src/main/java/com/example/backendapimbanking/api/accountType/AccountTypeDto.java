package com.example.backendapimbanking.api.accountType;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AccountTypeDto(@NotBlank(message = "account-types is reqiered") String name) {
}
