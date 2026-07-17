package org.example.exception;

import java.util.UUID;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(UUID id) {
        super("Notification %s not found".formatted(id));
    }
}
