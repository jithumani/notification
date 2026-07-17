package org.example.repository;

import org.example.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NotificationRepository {

    private final Map<UUID, Notification> store = new ConcurrentHashMap<>();

    public Notification save(Notification notification) {

        store.put(notification.getId(), notification);

        return notification;
    }

    public Optional<Notification> findById(UUID id) {

        return Optional.ofNullable(store.get(id));
    }
}
