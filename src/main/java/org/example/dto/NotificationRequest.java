package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(

        @NotBlank
        String userId,

        @NotBlank
        String message,

        @NotBlank
        String channel
) {
}
