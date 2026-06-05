package com.example.gasworks_worker_ui.entity;

import com.example.gasworks_worker_ui.Category.NotificationCategory;

import lombok.Data;

//import org.springframework.format.annotation.DateTimeFormat;

//import java.time.LocalDate;
//import java.time.LocalDateTime;

@Data
public class Notification {
    private String id;
    private String title;
    private String summary;
    private String content;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String publishedAt;
    private NotificationCategory category;
    private boolean isRead;

    public Notification(
        String id,
        String title,
        String summary,
        String content,
        String publishedAt,
        NotificationCategory category,
        boolean isRead
    ){
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.publishedAt = publishedAt;
        this.category = category;
        this.isRead = isRead;
    }

    public String getRelativePublishedAt() {
        if (publishedAt == null || publishedAt.isEmpty()) return "";
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            java.time.LocalDateTime date = java.time.LocalDateTime.parse(publishedAt, formatter);
            java.time.LocalDateTime now = java.time.LocalDateTime.now();

            long diffMins = java.time.Duration.between(date, now).toMinutes();
            long calendarDays = java.time.temporal.ChronoUnit.DAYS.between(date.toLocalDate(), now.toLocalDate());

            if (calendarDays == 0) {
                if (diffMins < 0) return "たった今";
                if (diffMins < 60) return diffMins + "分前";
                return (diffMins / 60) + "時間前";
            } else if (calendarDays == 1) {
                return "昨日";
            } else if (calendarDays < 30) {
                return calendarDays + "日前";
            } else {
                long diffMonths = java.time.temporal.ChronoUnit.MONTHS.between(date.toLocalDate(), now.toLocalDate());
                if (diffMonths < 12) {
                    return diffMonths + "ヶ月前";
                }
                long diffYears = java.time.temporal.ChronoUnit.YEARS.between(date.toLocalDate(), now.toLocalDate());
                return diffYears + "年前";
            }
        } catch (Exception e) {
            return publishedAt;
        }
    }
}

