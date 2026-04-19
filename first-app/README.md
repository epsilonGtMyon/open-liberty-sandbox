# first-app

Open Libertyを試す。

## はじめかた

[Create a starter application](https://openliberty.io/start/) ここから開始する。

Open Liberty自体はGradleのプラグインで落としてきてデプロイするので、個別で用意しなくてよさそう


## Gradle関係

Gradleプラグインの[ci.gradle](https://github.com/OpenLiberty/ci.gradle)これを見たらよさそう

- libertyDev dev modeで動かす
- libertyStop 止める。
- libertyDebug debug port (default:7777) で動かす。Eclipseなどでデバッグするときはこれでつないだらよさそう。


### いくつかメモ

GitHubみたら書いてるがいくつか抜粋

#### libertyのバージョンを変更する。

`installLiberty` あたりを見たらよさそう

``` gradle
liberty {
    runtime = ['version':'26.0.0.1']
}
```

このあたりは `gradle.properties` でも変更できそうだ

デフォルトだと最新の安定バージョンとなるそうだ


`useOpenLiberty` をfalseにしたらWebSphere Liberty 使えそう

## Libertyの設定関連

`src/main/liberty/config/server.xml` ファイルに記載する。

ここでフィーチャーの設定やデータソースの設定などが行うことができる。