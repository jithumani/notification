package org.example.exception;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationError {

    public record ApiError(

            LocalDateTime timestamp,

            int status,

            String code,

            String message,

            String path,

            List<FieldValidationError> fieldErrors

    ) {
    }
}
