# Spring_Enjoyable_SSG
## 概要
### 内容 
旅行で遊べる施設や食事・宿の記述をするサイトにチャレンジ。

元々はReactとJavaで分けて、Java側はRest ControllerでAPIとして
呼び出してたが、

Thymeleafの学習が必要になったため、仕方なく形式変更。

#### 元ブランチ
https://github.com/railgun-0402/Spring_Enjoyable_Site

### 目的
サイトの作成による、springのコーディング力をつけることや

現場で使っている技術をキャッチアップすること。

AWSも使用する予定だが、費用と開発初期段階のため未定。

## 使用技術(作成中なので予定も含む)
### バックエンド
<img src="https://skillicons.dev/icons?i=java,spring" /> <br /><br />

### フロントエンド
<img src="https://skillicons.dev/icons?i=javascript,jquery" /> <br /><br />

### その他インフラ
<img src="https://skillicons.dev/icons?i=docker,mysql" /> <br /><br />

## テスト
- spock/Junit5
  - テスト観点を身につける
  - 正直どちらUnitTestの方法はどちらでもいい
  - ただし、確認漏れを防ぐ「ケースを作成」を心掛ける

## フロントエンドブランチ
https://github.com/railgun-0402/React_Enjoyble

## feature
- 管理画面
  - 施設の登録・更新・削除
    - 施設の量は多いのでパンくずリストでも作る
  - ユーザの登録・更新・削除
    - ブラックリストやプランで使用できる機能を制限するのも面白いかも？
  - ゴミみたいなお問い合わせをPythonで読み分けて、優先度を下げるなどしてみたい
    - 優先度低いのはメール or Slackのみ等
- ユーザ
  - Top画面
  - 施設の詳細画面
  - 施設のお気に入り
  - 施設予約履歴
  - 会員ランク
  - アンケート
    - 施設の評価みたいなもの
  - おみくじ
    - サイトによくある娯楽をパクって実装してみる
  - お問い合わせ
    - メール送信
    - 管理者へメールが届くこと
    - formで良さそう
