# GasWorks プロジェクト開発ガイド

## 1. プロジェクト概要
本プロジェクトは、ガス検針業務を包括的にサポートするエンタープライズシステムです。
1つのリポジトリで複数のサービスを管理する「モノレポ（Monorepo）」構成を採用しています。

## 2. ディレクトリ構成と役割

| ディレクトリ名 | 役割 | 技術スタック | デフォルトポート |
| :--- | :--- | :--- | :--- |
| `gasworks-api` | 共通基盤REST API / DB管理 | Spring Boot | `8080` |
| `gasworks-worker-ui` | 作業員向けモバイルWeb UI | Spring Boot / Thymeleaf | `8081` |
| `gasworks-hp-ui` | 一般顧客向けホームページ | React / TypeScript | `5173` |
| `gasworks-admin-ui` | 管理者向け運用画面 | Django (Python) | `8000` |
| `gasworks-batch` | 請求・集計バッチ処理 | Spring Batch | (実行時のみ) |
| `DOCUMENT` | プロジェクト全体の共通設計書 | Markdown / PlantUML | - |

---

## 3. 開発の始め方（各プロジェクトの起動）

VSCodeで `gasworks` フォルダを開いた状態で、下部のターミナル（`Ctrl + @`）から各プロジェクトを起動します。
**重要：必ず各プロジェクトのディレクトリに移動（cd）してからコマンドを実行してください。**

### Project 3: API サーバー (最優先で起動)
```bash
cd gasworks-api
./mvnw spring-boot:run
```

### Project 1: 作業員向けUI
```bash
cd gasworks-worker-ui
./mvnw spring-boot:run
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

## 4. Git 基本操作ガイド

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

## 5. Tips

*   **わからないことはAIに質問！**: **何がどうわからないか**を明確にし、**限定的**かつ**専門的な用語**をなるべく入れて、まずはAIに聞いてみてください。
*   **ディレクトリに注意**: ターミナルで `mvnw` や `npm` コマンドが「見つかりません」となる場合、ほとんどが実行ディレクトリ間違い（`cd` 忘れ）です。
*   **ポートの競合**: 他のアプリでポートを使っているとエラーになります。その場合はプロジェクトの `application.properties` 等でポート番号を変更してください。
*   **DB接続**: APIを起動する前に、Docker等でMySQLが立ち上がっていることを確認してください。
*   **困ったら `git status`**: Gitで今何が起きているか分からなくなったら、まずこのコマンドを打って状況を確認しましょう。

---