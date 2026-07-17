package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.model.NotificationChannel;

public record NotificationRequest(

        @NotBlank(message = "User Id is required")
        String userId,

        @NotBlank(message = "Message is required")
        @Size(min = 5, max = 500,
                message = "Message must be between 5 and 500 characters")
        String message,

        @NotNull(message = "Channel is required")
        NotificationChannel channel
) {
}
