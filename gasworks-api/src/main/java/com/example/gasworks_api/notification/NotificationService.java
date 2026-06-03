package com.example.gasworks_api.notification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationResponse::new)
                .collect(Collectors.toList());
    }
}
