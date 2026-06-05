# API仕様書

## 1. 概要

本仕様書は、Spring Bootで実装するRESTful APIの仕様を定義するものです。
フロントエンド（Worker UI、HP向けUI、管理者向けUI）は、このAPIを介してデータの取得・更新を行います。

## 2. 認証

- すべてのエンドポイントは、原則として認証が必要です。
- 認証方式は `JWT (JSON Web Token)` を利用します。
- ログインAPIで取得したトークンを、以降のリクエストヘッダーに `Authorization: Bearer <token>` の形式で付与します。

## 3. エンドポイント一覧

### 3.1. 認証 (`/auth`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `POST` | `/auth/login` | ログイン処理。成功時、JWTを返す。 |
| `POST` | `/auth/logout` | ログアウト処理。 |

### 3.2. 作業割当 (`/assignments`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/assignments` | ログイン中の作業員に割り当てられた作業一覧（当日分など）を取得する。 |
| `GET` | `/assignments/summary` | 本日の作業進捗（完了数/全体数）を取得する（日報用）。 |
| `GET` | `/assignments/{id}` | 指定した割当の詳細情報を取得する。 |

### 3.3. 検針データ (`/meter-readings`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/meter-readings` | 検針データの一覧を取得する。 |
| `GET` | `/meter-readings/{id}` | 指定したIDの検針データを取得する。 |
| `POST` | `/meter-readings` | 新しい検針データを登録する（写真URL、割当IDを含む）。 |

### 3.4. お知らせ (`/notifications`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/notifications` | ログインユーザー向けのお知らせ一覧を取得する（既読状態を含む）。 |
| `GET` | `/notifications/{id}` | お知らせの詳細を取得する。 |
| `POST` | `/notifications/{id}/read` | 指定したお知らせを既読状態にする。 |

### 3.5. 緊急連絡 (`/emergency`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `POST` | `/emergency` | 現在地情報と共に緊急事態を本部に通報する。 |

### 3.6. 顧客情報 (`/customers`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/customers` | 顧客情報の一覧を取得する。 |
| `GET` | `/customers/{id}` | 指定したIDの顧客情報を取得する。 |

---

## 4. データ形式 (JSON)

### 認証レスポンス
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

### 作業割当 (Assignment)
```json
{
  "id": 501,
  "scheduledDate": "2023-10-27",
  "status": "PENDING",
  "customer": {
    "id": 101,
    "name": "山田 太郎",
    "address": "東京都千代田区...",
    "latitude": 35.681236,
    "longitude": 139.767125
  }
}
```

### 作業サマリー (AssignmentSummary)
```json
{
  "totalCount": 20,
  "completedCount": 15,
  "skippedCount": 1
}
```

### 検針データ (MeterReading)

```json
{
  "id": 1,
  "assignmentId": 501,
  "value": 1234.5,
  "imagePath": "https://storage.example.com/meter/photo1.jpg",
  "readingAt": "2023-10-27T10:00:00Z"
}
```

### 顧客 (Customer)

```json
{
  "id": 101,
  "name": "山田 太郎",
  "address": "東京都千代田区...",
  "contact": "03-1234-5678",
  "createdAt": "2023-10-27T10:00:00Z",
  "updatedAt": "2023-10-27T10:00:00Z"
}
```