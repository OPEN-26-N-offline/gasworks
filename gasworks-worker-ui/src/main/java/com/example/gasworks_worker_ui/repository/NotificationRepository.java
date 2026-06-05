package com.example.gasworks_worker_ui.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.gasworks_worker_ui.Category.NotificationCategory;
import com.example.gasworks_worker_ui.entity.Notification;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * お知らせリポジトリ
 */
@Repository
public class NotificationRepository {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "http://localhost:8081/api/notifications";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * 全てのお知らせを取得
     */
    public List<Notification> findAll() {
        ResponseEntity<List<Notification>> response = restTemplate.exchange(
            API_URL,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Notification>>() {}
        );
        List<Notification> notifications = response.getBody();

        if (notifications != null) {
            // 現在時刻
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

            for (Notification notification : notifications) {
                try {
                    // APIから取得した日付文字列を解析 (ISO 8601等)
                    // API側のZonedDateTimeのシリアライズ形式（例: "2024-07-26T10:00:00+09:00" またはタイムゾーン付き）
                    // 柔軟に対応するためOffsetDateTimeでパースしてから変換
                    ZonedDateTime publishedTime = OffsetDateTime.parse(notification.getPublishedAt()).atZoneSameInstant(ZoneId.of("Asia/Tokyo"));
                    String relativeTime = calculateRelativeTime(publishedTime, now);
                    notification.setPublishedAt(relativeTime);
                } catch (Exception e) {
                    // パースエラーの場合はそのまま（または適切に処理）
                }
            }
        }
        return notifications;

        // 以前のモック実装
        /*
        LocalDateTime now = LocalDateTime.now();
        // プロトタイプの表示内容に合わせた動的モックデータ（相対日付表示のテスト用）
        return Arrays.asList(
            new Notification(
                "1",
                "【重要】台風接近に伴う作業中止の判断について",
                "本日の午後以降の検針作業は、天候の状況により各自の判断で...",
                "本日の午後以降の検針作業は、天候の状況により各自の判断で中止してください。安全第一で行動し、無理な作業は控えるようお願いします。詳細は添付のPDFをご確認ください。",
                now.minusHours(1).format(FORMATTER),
                NotificationCategory.ALERT,
                false
            ),
            new Notification(
                "2",
                "新システム移行に伴うマニュアル公開",
                "来月からの新メーター導入に伴い、操作手順書が更新されました。",
                "来月からの新メーター導入に伴い、ハンディターミナルの操作手順書が更新されました。各自ダウンロードの上、内容を確認してください。",
                now.minusDays(1).format(FORMATTER),
                NotificationCategory.INFO,
                false
            ),
            new Notification(
                "3",
                "モバイルバッテリー配布のお知らせ",
                "各支社にて新しい大容量バッテリーの配布を開始しました。",
                "各支社にて新しい大容量バッテリーの配布を開始しました。現在使用しているバッテリーと交換になりますので、支社に戻った際に受け取ってください。",
                now.minusDays(3).format(FORMATTER),
                NotificationCategory.OFFICE,
                false
            )
        );
        */
    }

    private String calculateRelativeTime(ZonedDateTime past, ZonedDateTime now) {
        long seconds = ChronoUnit.SECONDS.between(past, now);
        if (seconds < 60) {
            return "ちょうど今";
        }
        long minutes = ChronoUnit.MINUTES.between(past, now);
        if (minutes < 60) {
            return minutes + "分前";
        }
        long hours = ChronoUnit.HOURS.between(past, now);
        if (hours < 24) {
            return hours + "時間前";
        }
        long days = ChronoUnit.DAYS.between(past, now);
        if (days < 365) {
            return days + "日前";
        }
        long years = ChronoUnit.YEARS.between(past, now);
        return years + "年前";
    }
}
