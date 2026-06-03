package com.example.gasworks_api.notification;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NotificationResponse {
    private String id;
    private String title;
    private String summary;
    private String content;
    @JsonProperty("publishedAt")
    private ZonedDateTime publishedAt;
    private NotificationCategory category;
    @JsonProperty("isRead")
    private boolean isRead;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId().toString();
        this.title = notification.getTitle();
        this.summary = notification.getSummary();
        this.content = notification.getContent();
        this.publishedAt = notification.getPublishedAt();
        this.category = notification.getCategory();
        this.isRead = false; // 本来はユーザーごとの既読状態を反映
    }
}
