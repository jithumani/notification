package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.NotificationRequest;
import org.example.dto.NotificationResponse;
import org.example.model.Notification;
import org.example.ratelimit.RateLimited;
import org.example.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @RateLimited(key = "notifications-create")
    public ResponseEntity<NotificationResponse> create(
            @Valid @RequestBody NotificationRequest request) {

        NotificationResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public Notification get(@PathVariable UUID id) {

        return service.get(id);
    }

}
