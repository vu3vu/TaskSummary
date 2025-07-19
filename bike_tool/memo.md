MD使い方
---
marp : true
theme: gaia


---
Ctl + Shift + P 
Markdown:プレビューを横に表示

拡張機能
Markdown all in one

プログラミング応援チャンネル
とにかく動かせ
https://www.youtube.com/watch?v=bYFcmmUAnvE&list=PLKGspLlIkheJuDe3rLDBFetEHy_RX5xwo&index=2


#学習の流れ
１設計
２JAVAプロジェクト作成
３画面の見た目
４画面を動的ページに
５DB
６入力チェック
７ログイン・ログアウト


template <--controller --> service  ---> Mapper
                form         bean           Mapper.xml

template 画面デザイン
controller リクエストレスポンス画面遷移
Service ユーザに提供するサービス　ビジネスロジック
Mapper DBを扱う。実行するにはxmlに記述
Form 画面から渡される入力情報のまとまり
Bean 処理結果など情報のまとまり


１設計

設計書
※プログラムの変更による変更が軽減されるようなもの
※設計書とプログラムが合わないときが多いのでそうならないように注意
目的
伝達：仕様を開発者に伝達
統一：作りかたを統一
把握：メンテナンスが容易なように地図を作る

２JAVAプロジェクト作成

lanch.jsonと起動設定を実施してF5で実行できるようにする

当初、DBがつながらないエラーがでるのでいったんDBをつかわないように設定
Description:
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.       
Reason: Failed to determine a suitable driver class

ーーー＞mybatisをコメントか

git 改行コード自動変換



３画面の見た目
４画面を動的ページに
５DB
６入力チェック
７ログイン・ログアウト



