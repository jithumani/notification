package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.NotificationRequest;
import org.example.dto.NotificationResponse;
import org.example.model.Notification;
import org.example.model.NotificationStatus;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationResponse create(NotificationRequest request) {

        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .userId(request.userId())
                .message(request.message())
                .channel(request.channel())
                .status(NotificationStatus.QUEUED)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);

        return new NotificationResponse(
                notification.getId(),
                notification.getStatus()
        );
    }

    public Notification get(UUID id) {

        return repository.findById(id)
                .orElseThrow();
    }

}
