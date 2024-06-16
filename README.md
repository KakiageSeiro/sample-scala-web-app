# 目的？
- ScalaでのWebアプリ開発の全体感を感じる
- 気になったライブラリとかをこのリポジトリで試すことで、実際の使用感を感じる

# TODO
- [ ] Playの読んだ方が良さそうドキュメントを読む
  - [x] Working with Json(Jsonを返すAPIつくるよ)(序盤だけよんだ)
  - [x] Accessing an SQL database(ORMのSlickをつかってみるよ)
  - [x] Calling REST APIs with Play WS(Play WSというやつ知らないので知るまで)→HTTPコールするやつ。WSはWebServiceライブラリのことみたい
  - [x] Dependency Injection(ジュースをつかってみるよ)
  - [x] Application Settings
  - [x] Testing your application(ジュースを踏まえたテストの書き方がありそう)
  - [x] Logging
  - [ ] Integrating with Pekko(Pekkoを入門)(これがっつりコード変わるからCatsの後にしたほうが良いかも)
- [x] PlayがWebフレームワークのデファクトみたいなので入れる
    - [x] 適当なAPIを作る
    - [x] 適当なAPI(Json返す)を作る
    - [x] テストを書く
- [x] ORMを入れる
    - [x] ScalikeJDBCがデファクト？
        - [x] SQLが書けるORMが好みなので、可能か調べる
    - [x] APIからDBを触れるようにする
- [ ] Catsを入れる
    - [ ] モナドとかを学んだが、実際にコードを読み書きするときにどれくらい嬉しいかをイメージしきれていないので解消したい
        - [ ] Catsは多分モナドとかをユーザー定義として作らなくても、事前に用意しておきましたよ系ライブラリだと思うが、これを確認したい
    - [ ] Option,Either,Futureを組み合わせるコードを書けば体感できそう？
        - [ ] Futureは面倒くさそうなのでsleepさせる疑似コードでもいいかも
        - [ ] OptionはDBでSELECTでよさそう
        - [ ] EitherはDBでUPDATEでよさそう
- [ ] Scalaを使ってる会社が採用しているライブラリとかを調べて試してみる
  - [ ] Effがきになる。副作用を良い感じに扱う











