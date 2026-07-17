package org.example.dto;

import org.example.model.NotificationStatus;

import java.util.UUID;

public record NotificationResponse(

        UUID id,
        NotificationStatus status

) {
}
