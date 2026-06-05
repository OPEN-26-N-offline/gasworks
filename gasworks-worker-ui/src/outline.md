# お知らせ画面 (notifications.html) 設計書

受講生向け：このドキュメントは、作業員が本部からの通知を確認する「お知らせ画面」の振る舞いとデータを定義したものです。

## 1. 画面機能仕様書
ユーザー（作業員）の操作に対して、画面がどのように動くべきかを定義します。

| 機能ID | 機能名 | 内容 | 備考 |
| :--- | :--- | :--- | :--- |
| F-01 | お知らせ一覧表示 | 画面起動時に、最新のお知らせリストを一覧形式で表示する。 | 日時が新しい順に並べる |
| F-02 | 重要度による色分け | 緊急(ALERT)・重要(WARN)・通常(INFO)の3段階で、ラベルの色やアイコンを切り替える。 | BootstrapのBadgeなどを活用 |
| F-03 | 未読・既読の表現 | まだ確認していないお知らせには「未読マーク」を表示する。 | データモデルの `isRead` を参照 |
| F-04 | 詳細表示 | 項目を選択した際、お知らせの全文を表示する。 | モーダル表示、または一覧の下に展開 |

## 2. データ要素定義
プログラム内（JavaScriptやJavaのエンティティ）で扱う「お知らせ」1件あたりのデータ構造です。

| 項目ID | 項目名 | 型 | 説明 | サンプル値 |
| :--- | :--- | :--- | :--- | :--- |
| id | ID | String | お知らせを一意に識別するためのコード | "NOTIF-001" |
| title | タイトル | String | 連絡事項の件名。一覧で最も目立つ項目。 | "明日の点検作業の順序変更" |
| summary | 概要 | String | 本文の冒頭部分。一覧でプレビューとして表示。 | "中央区エリアの点検順が..." |
| content | 本文 | String | お知らせの全文。 | "明日の10時から予定していた..." |
| publishedAt | 発信日時 | String | お知らせが公開された日時。 | "2026-03-10 09:00" |
| category | カテゴリ | Enum | 緊急度の分類（ALERT, WARN, INFO） | "ALERT" |
| isRead | 既読フラグ | Boolean | 作業員が内容を確認したかどうか。 | false |
用)
## 3. APIインターフェース定義 (将来
※ **【重要】** 現段階ではAPIサーバーは使用せず、Repositoryクラスに固定値を実装して動作させます。以下は将来APIを接続する際の先行定義です。

### 3.1 お知らせ一覧取得API
- **URL**: `// GET /api/worker/notifications` (※現在は不使用。コメントアウト状態で定義)
- **Method**: `GET`
- **Response (JSONサンプル)**:
```json
[
  {
    "id": "1",
    "title": "【緊急】ガス漏れ通報への対応優先依頼",
    "summary": "港区芝大門付近で通報あり。付近の作業員は...",
    "publishedAt": "2026-03-10 08:30",
    "category": "ALERT",
    "isRead": false
  },
  {
    "id": "2",
    "title": "週報提出の期限について",
    "summary": "今週の週報は金曜17時までに提出してください。",
    "publishedAt": "2026-03-09 15:00",
    "category": "INFO",
    "isRead": true
  }
]
```

### 3.3 リポジトリでのモック実装例 (Java)
バックエンドの実装時には、以下のようなコードでデータをリストとして保持し、画面へ返却します。

```java
/**
 * お知らせリポジトリ（モック実装）
 */
@Repository
public class NotificationRepository {
    // HTMLプロトタイプの「通知バッジ: 2」に合わせた固定データ
    private static final List<Notification> MOCK_DATA = Arrays.asList(
        new Notification(
            "1",
            "【緊急】ガス漏れ通報への対応優先依頼",
            "港区芝大門付近で通報あり。付近の作業員は...",
            "港区芝大門2丁目付近でガス臭の通報がありました。最寄りの作業員は現在進めている作業を中断し、至急現場へ向かってください。",
            "2026-03-10 08:30",
            "ALERT",
            false
        ),
        new Notification(
            "2",
            "週報提出の期限について",
            "今週の週報は金曜17時までに提出してください。",
            "システム改修に伴い、今週から週報の提出期限が1時間早まります。金曜17時までに同期を完了させてください。",
            "2026-03-09 15:00",
            "INFO",
            false
        )
    );

    /**
     * 全てのお知らせを取得
     */
    public List<Notification> findAll() {
        // 将来的にはここで // GET /api/worker/notifications を呼び出すが、
        // 現段階では固定リストをそのまま返す
        return MOCK_DATA;
    }
}
```

## 4. ルーティング定義
画面へのアクセスURLと、遷移の前後関係を定義します。

| 項目 | 内容 | 備考 |
| :--- | :--- | :--- |
| 画面URL | `notifications.html` | 将来的には `/worker/notifications` 等 |
| 遷移元（IN） | ポータル画面 (`index.html`) | 「お知らせ」カードの「確認」ボタンより |
| 遷移先（OUT） | ポータル画面 (`index.html`) | ヘッダーのロゴまたは「戻る」操作 |

## 5. 追加・修正ファイル一覧

実装の際に作成・変更が必要なファイルの一覧です。

| 分類                       | ディレクトリ / ファイルパス                                                             | 内容                                                   |
| :----------------------- | :-------------------------------------------------------------------------- | :--------------------------------------------------- |
| **Backend (Controller)** | `src/main/java/com/example/gasworks/controller/NotificationController.java` | お知らせ画面へのリクエストを受け取り、Repositoryから取得したお知らせ一覧をViewへ渡します。 |
| **Backend (Model)**      | `src/main/java/com/example/gasworks/entity/Notification.java`                | お知らせのデータ構造（Entity）を定義します。                            |
| **Backend (Repo)**       | `src/main/java/com/example/gasworks/repository/NotificationRepository.java` | データを保持するリポジトリ。現在はリストで固定値を返します。                       |
| **Frontend (View)**      | `src/main/resources/templates/worker/notifications.html`                    | ブラウザで表示するHTMLファイルです。                                 |
