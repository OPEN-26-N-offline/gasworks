# API仕様書

## 1. 概要

本仕様書は、Spring Bootで実装するRESTful APIの仕様を定義するものです。
フロントエンド（HP向けUI、管理者向けUI）は、このAPIを介してデータの取得・更新を行います。

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

### 3.2. 検針データ (`/meter-readings`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/meter-readings` | 検針データの一覧を取得する。 |
| `GET` | `/meter-readings/{id}` | 指定したIDの検針データを取得する。 |
| `POST` | `/meter-readings` | 新しい検針データを登録する。 |
| `PUT` | `/meter-readings/{id}` | 検針データを更新する。 |
| `DELETE` | `/meter-readings/{id}` | 検針データを削除する。 |

### 3.3. 顧客情報 (`/customers`)

| メソッド | URI | 説明 |
|:---|:---|:---|
| `GET` | `/customers` | 顧客情報の一覧を取得する。 |
| `GET` | `/customers/{id}` | 指定したIDの顧客情報を取得する。 |
| `POST` | `/customers` | 新しい顧客を登録する。 |
| `PUT` | `/customers/{id}` | 顧客情報を更新する。 |

---

## 4. データ形式 (JSON)

### 検針データ (MeterReading)

```json
{
  "id": 1,
  "customerId": 101,
  "readingDate": "2023-10-27T10:00:00Z",
  "value": 1234.5,
  "workerId": 1,
  "createdAt": "2023-10-27T10:00:00Z",
  "updatedAt": "2023-10-27T10:00:00Z"
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