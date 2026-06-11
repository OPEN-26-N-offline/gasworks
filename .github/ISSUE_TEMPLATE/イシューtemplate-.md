---
name: 'イシューTemplate '
about: イシューを立てる時のテンプレートです。
title: ''
labels: ''
assignees: ''

---

## 📄 概要 (Overview)
## 🛠 作業内容 (Tasks)
- [ ] `CustomerCategory.java` (Enum) の新規作成
  - START, GOAL, WAYPOINT の定義
- [ ] `Customer.java` (Entity) のフィールド追加
  - `latitude` (緯度), `longitude` (経度), `sequence` (巡回順序), `type` (CustomerCategory) の追加とgetter/setter定義
- [ ] `CustomerMapper.java` の修正
  - 新規フィールドに対応するマッピング処理の追記
- [ ] `schema.sql` の修正
  - `customers` テーブルに新規4カラムを追加
- [ ] `data.sql` の修正
  - テストデータにルートマップ用の初期データを追加

## ✅ 完了定義 (Definition of Done)
- [ ] コンパイルエラーなくアプリケーションが起動すること
- [ ] `data.sql` のデータがエラーなくH2/MySQL等のDBにインポートされること
- [ ] 顧客情報取得APIのレスポンスに、追加した要素（latitude, longitude, sequence, type）が正しく含まれていること（Postmanやブラウザで確認）

## 🔗 関連情報・参考URL (References)
- 関連Issue: #
