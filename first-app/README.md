# first-app

Open Libertyを試す。

## はじめかた

[Create a starter application](https://openliberty.io/start/) ここから開始する。

Open Liberty自体はGradleのプラグインで落としてきてデプロイするので、個別で用意しなくてよさそう

## とりあえず動かす

`libertyDev` のGradleタスクを実行し [http://localhost:9080/first-app/](http://localhost:9080/first-app/) にアクセスする。


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


#### mainソースセットのresourcesDirの変更

`src/main/resources` ディレクトリにファイルを配置すると `libertyDev` などを実行すると `war`タスクでファイル重複エラーが発生するので
書き換える


```
ext  {
    // src/main/resourcesのコピーが重複するので
    sourceSets.main.output.resourcesDir = sourceSets.main.java.destinationDirectory
}
```

この書き方は古い書き方なので必要に応じて変更する。

- [参考1](https://github.com/OpenLiberty/ci.gradle/issues/842#issuecomment-1701592178) 
- [参考2](https://github.com/OpenLiberty/ci.gradle/issues/301#issuecomment-468616628)
 
これが本当に正しいかは不明.. バージョン上げたら解決したりするのかな..
 

## Libertyの設定関連

`src/main/liberty/config/server.xml` ファイルに記載する。

ここでフィーチャーの設定やデータソースの設定などが行うことができる。