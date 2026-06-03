package com.example.gasworks_api.notification;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String summary;
    private String content;
    @Enumerated(EnumType.STRING)
    private NotificationCategory category;
    private ZonedDateTime publishedAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
