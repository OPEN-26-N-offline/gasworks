-- テスト用ユーザー（作業員と管理者）
INSERT INTO users (name, email, password, role) VALUES
    ('山田 太郎', 'yamada@example.com', 'hashed_password_123', 'WORKER'),
    ('佐藤 一郎', 'sato@example.com', 'hashed_password_456', 'ADMIN');

-- テスト用顧客情報
INSERT INTO customers (name, address, contact) VALUES
    ('東京 ガス男', '東京都中央区築地...', '03-1111-2222'),
    ('浪速 ガス子', '大阪府大阪市北区...', '06-3333-4444');

-- テスト用検針データ（東京ガス男さんのメーターを、山田作業員が検針したデータ）
INSERT INTO meter_readings (customer_id, user_id, reading_date, `value`) VALUES -- ★ ここを `value` に変更！
    (1, 1, '2026-06-01', 123.45);