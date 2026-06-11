# API仕様書

## 1. 概要

本仕様書は、Spring Bootで実装するRESTful APIの仕様を定義するものです。
フロントエンド（Worker UI、HP向けUI、管理者向けUI）は、このAPIを介してデータの取得・更新を行います。

## 2. 認証

- すべてのエンドポイントは、原則として認証が必要です。
- 認証方式は `JWT (JSON Web Token)` を利用します。
- ログインAPIで取得したトークンを、以降のリクエストヘッダーに `Authorization: Bearer <token>` の形式で付与してください。

## 3. エンドポイント一覧

### 3.1. ログイン (`POST /api/auth/login`)
ユーザー認証を行い、JWTトークンを発行します。

#### 3.1.1. リクエスト
```json
{
  "email": "worker@example.com",
  "password": "password123"
}
```

#### 3.1.2. レスポンス
ステータスコード: 200 OK
```json
{
  "token": "eyJhbGci...",
  "user": {
    "id": 1,
    "name": "作業員A",
    "role": "WORKER"
  }
}
```

#### 3.1.3. フィールド定義
| フィールド名 | 型 | 説明 | 必須 |
| :--- | :--- | :--- | :--- |
| token | String | JWT認証トークン | ○ |
| user.id | Long | ユーザーID | ○ |
| user.name | String | 表示名 | ○ |
| user.role | String | 権限 (ADMIN, WORKER) | ○ |

---

### 3.2. 作業割当一覧取得 (`GET /api/assignments`)
ログイン中の作業員に割り当てられた作業一覧を取得します。

#### 3.2.1. リクエスト
クエリパラメータ: `scheduledDate` (任意。形式: YYYY-MM-DD)

#### 3.2.2. レスポンス
ステータスコード: 200 OK
```json
[
  {
    "id": 501,
    "scheduledDate": "2024-10-27",
    "status": "PENDING",
    "customer": {
      "id": 101,
      "name": "山田 太郎",
      "address": "東京都千代田区...",
      "latitude": 35.681236,
      "longitude": 139.767125
    }
  }
]
```

#### 3.2.3. フィールド定義
| フィールド名 | 型 | 説明 | 必須 |
| :--- | :--- | :--- | :--- |
| id | Long | 作業割当ID | ○ |
| scheduledDate | String | 予定日 (ISO 8601) | ○ |
| status | String | 状態 (PENDING, COMPLETED, SKIPPED) | ○ |
| customer.name | String | 顧客名 | ○ |
| customer.latitude | Double | 緯度 | △ |
| customer.longitude| Double | 経度 | △ |

---

### 3.3. 作業サマリー取得 (`GET /api/assignments/summary`)
本日の作業進捗状況を取得します。

#### 3.3.1. レスポンス
ステータスコード: 200 OK
```json
{
  "totalCount": 20,
  "completedCount": 15,
  "skippedCount": 1
}
```

#### 3.3.2. フィールド定義
| フィールド名 | 型 | 説明 | 必須 |
| :--- | :--- | :--- | :--- |
| totalCount | Integer | 全作業数 | ○ |
| completedCount | Integer | 完了済み数 | ○ |
| skippedCount | Integer | スキップ数 | ○ |

---

### 3.4. 作業詳細取得 (`GET /api/assignments/{id}`)
指定した作業割当の詳細情報を取得します。

#### 3.4.1. レスポンス
ステータスコード: 200 OK
```json
{
  "id": 501,
  "scheduledDate": "2024-10-27",
  "status": "PENDING",
  "customer": {
    "id": 101,
    "name": "山田 太郎",
    "address": "東京都千代田区...",
    "contact": "03-1234-5678",
    "latitude": 35.681236,
    "longitude": 139.767125
  }
}
```

---

### 3.5. 検針データ登録 (`POST /api/meter-readings`)
検針結果とメーター写真を登録します。登録成功時、該当する assignments のステータスは COMPLETED に更新されます。

---

### 3.6. お知らせ一覧取得 (`GET /api/notifications`)
作業員向けのお知らせ一覧を取得します。

#### 3.6.1. レスポンス
```json
[
  {
    "id": "1",
    "title": "【重要】台風接近に伴う作業中止の判断について",
    "summary": "台風接近に伴う対応について",
    "content": "本日の午後以降の検針作業は、天候の状況により各自の判断で中止してください...",
    "publishedAt": "2024-07-26T10:00:00+09:00",
    "category": "IMPORTANT",
    "isRead": false
  }
]
```

---

### 3.7. 緊急通報 (`POST /api/emergency`)
現場での事故やトラブルを本部に通知します。

#### 3.6.2. レスポンス
ステータスコード: 200 OK

#### 3.6.3. フィールド定義
| フィールド名 | 型 | 説明 | 必須 |
| :--- | :--- | :--- | :--- |
| latitude | Double | 現在地の緯度 | ○ |
| longitude | Double | 現在地の経度 | ○ |
| message | String | 通報メッセージ | △ |