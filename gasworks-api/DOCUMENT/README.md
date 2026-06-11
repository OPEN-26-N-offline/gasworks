# GasWorks プロジェクト開発ガイド

## 1. プロジェクト概要
本プロジェクトは、ガス検針業務を包括的にサポートするエンタープライズシステムです。
1つのリポジトリで複数のサービスを管理する「モノレポ（Monorepo）」構成を採用しています。

## 2. ディレクトリ構成と役割

| ディレクトリ名 | 役割 | 技術スタック | デフォルトポート |
| :--- | :--- | :--- | :--- |
| `gasworks-api` | 共通基盤REST API / DB管理 | Spring Boot / H2 (MySQL Mode) | `8080` |
| `gasworks-worker-ui` | 作業員向けモバイルWeb UI | Spring Boot / Thymeleaf / H2 | `8081` |
| `gasworks-hp-ui` | 一般顧客向けホームページ | React 19 / TypeScript 6 / Vite 8 | `5173` |
| `gasworks-admin-ui` | 管理者向け運用画面 | Django (Python) | `8000` |
| `gasworks-batch` | 請求・集計バッチ処理 | Spring Batch / H2 | (実行時のみ) |
| `DOCUMENT` | プロジェクト全体の共通設計書 | Markdown / PlantUML | - |

---

## 3. 設計ドキュメント
詳細な仕様については `DOCUMENT` フォルダ内の各ファイルを参照してください。
- API全体仕様書
- お知らせAPI詳細
- データベース定義

---

## 4. 開発の始め方（各プロジェクトの起動）

VSCodeで `gasworks-api` ディレクトリを作業場として起動します。
**重要：ターミナルは PowerShell (推奨) または コマンドプロンプト を使用してください。**

### API サーバーの起動
```bash
cd gasworks-api
mvnw spring-boot:run
```

### Project 1: 作業員向けUI
```bash
cd gasworks-worker-ui
mvnw spring-boot:run
```

### Project 5: ホームページ (React)
```bash
cd gasworks-hp-ui
npm install    # 初回のみ
npm run dev
```

### Project 2: 管理画面 (Django)
```bash
cd gasworks-admin-ui
python manage.py runserver
```

---

## 5. Git 基本操作ガイド

### コマンドでの操作
1. **状態確認**: 自分の変更したファイルを確認します。
   ```bash
   git status
   ```
2. **ステージング**: コミットするファイルを登録します。
   ```bash
   git add .
   ```
3. **コミット**: 変更内容に名前を付けて保存します。
   ```bash
   git commit -m "feat: お知らせ画面にバッジを表示"
   ```
4. **プッシュ**: サーバーに自分の変更を送信します。
   ```bash
   git push origin dev
   ```

### VSCode 画面での操作
1. 左側の「ソース管理（枝分かれアイコン）」をクリック。
2. 変更されたファイルの横にある `+` を押し「ステージング」へ。
3. 上部のテキストボックスにメッセージを入力し、`コミット` ボタンを押す。
4. `変更を同期` または右上の `...` から `プッシュ` を選択。

---

## 6. Tips

*   **わからないことはAIに質問！**: **何がどうわからないか**を明確にし、**限定的**かつ**専門的な用語**をなるべく入れて、まずはAIに聞いてみてください。
*   **ディレクトリに注意**: ターミナルで `mvnw` や `npm` コマンドが「見つかりません」となる場合、ほとんどが実行ディレクトリ間違い（`cd` 忘れ）です。
*   **DBの確認方法**: VSCode拡張機能の **「Database Client」** を使うと、H2もMySQLもGUIで簡単に確認できます。
*   **APIのDB接続**: 現在のコード設定ではインメモリDB（H2）を使用しており、再起動のたびにデータが初期化されます。
*   **ポートの競合**: `gasworks-api` は `8081` ポートを使用します。他のプロジェクトと重複しないよう注意してください。
*   **困ったら `git status`**: Gitで今何が起きているか分からなくなったら、まずこのコマンドを打って状況を確認しましょう。

---