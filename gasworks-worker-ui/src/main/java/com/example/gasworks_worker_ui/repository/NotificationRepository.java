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
 * お知らせ情報を取得するためのリポジトリクラス。
 * <p>
 * このクラスは、お知らせに関するデータの永続化や取得を担当します。
 * 現在の実装では、外部のAPIサーバーからお知らせ情報を取得しています。
 * SpringのDIコンテナによって管理される`@Repository`コンポーネントです。
 * </p>
 */
@Repository
public class NotificationRepository {

    /**
     * RESTful APIを呼び出すためのクライアント。
     * Springが提供する同期的なHTTPクライアントで、APIとの通信に使用します。
     */
    // private final

    /**
     * 呼び出し先のAPIサーバーのエンドポイントURL。
     * localhost:8081で動作しているお知らせAPIを指定しています。
     */
    // private static final

    /**
     * 日付/時刻のフォーマットを指定するためのフォーマッター。
     * このリポジトリの以前のモック実装で使用されていました。
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * 全てのお知らせ情報を取得し、各お知らせの公開日時を相対時間表記に変換して返します。
     * <p>
     * 処理の流れ：
     * 1. `RestTemplate`を使用して、指定されたAPIエンドポイントからお知らせのリストをJSON形式で取得します。
     * 2. 取得したお知らせリストがnullでない場合、各お知らせオブジェクトの公開日時（`publishedAt`）を相対時間（例：「1時間前」）に変換します。
     * 3. 変換後のリストを返します。
     * </p>
     *
     * @return 相対時間表記に変換されたお知らせのリスト。APIからの応答がnullの場合はnullを返します。
     */
    public List<Notification> findAll() {
        // APIからお知らせリストを取得
        // exchangeメソッドは、HTTPリクエストを送信し、レスポンスをResponseEntityとして受け取ります。
        // ParameterizedTypeReferenceを使用することで、ジェネリクスを含む型（ここではList<Notification>）を正しくデシリアライズできます。








        // APIからのレスポンスが正常に取得できた場合のみ処理を実行
        if (notifications != null) {
            // 相対時間計算の基準となる現在時刻を取得（タイムゾーンは東京を指定）
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

            // 拡張for文でリスト内の各お知らせオブジェクトを処理
            for (Notification notification : notifications) {
                try {
                    // APIから返されるpublishedAtはタイムゾーン情報を含むISO 8601形式の文字列（例: "2024-07-26T10:00:00+09:00"）
                    // これをパースしてZonedDateTimeオブジェクトに変換します。
                    // タイムゾーンの扱いに柔軟性を持たせるため、一度OffsetDateTimeでパースし、その後東京時間でのZonedDateTimeに変換しています。
                    ZonedDateTime publishedTime = OffsetDateTime.parse(notification.getPublishedAt()).atZoneSameInstant(ZoneId.of("Asia/Tokyo"));
                    
                    // 相対時間を計算するプライベートメソッドを呼び出し
                    String relativeTime = calculateRelativeTime(publishedTime, now);
                    
                    // 計算した相対時間の文字列でpublishedAtフィールドを上書き
                    notification.setPublishedAt(relativeTime);
                } catch (Exception e) {
                    // 日付文字列のパースに失敗した場合など、予期せぬ例外が発生した場合は、
                    // エラーをログに出力するなどの処理が考えられますが、ここでは元の値のまま処理を続行します。
                }
            }
        }
        return notifications;

        // =================================================================
        // 以前のモック実装（現在はコメントアウト）
        // API接続ができない場合や、オフラインでの開発時に使用されていました。
        // =================================================================
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

    /**
     * 過去の日時と現在の日時を比較し、相対的な時間差を表す文字列を生成します。
     * <p>
     * 例：
     * - 60秒未満 → "ちょうど今"
     * - 60分未満 → "X分前"
     * - 24時間未満 → "X時間前"
     * - 365日未満 → "X日前"
     * - 365日以上 → "X年前"
     * </p>
     *
     * @param past 過去の日時（ZonedDateTime）
     * @param now  現在の日時（ZonedDateTime）
     * @return 計算された相対時間の文字列
     */
    private String calculateRelativeTime(ZonedDateTime past, ZonedDateTime now) {
        // ChronoUnitを使用して、2つの日時の間の時間差を各単位で計算
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
