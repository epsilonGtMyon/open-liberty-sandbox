# mybatis-integration

`mybatis`との連携をする。

[MyBatis CDI Extension](https://github.com/mybatis/cdi) を使う

## gradle 依存関係


`mybatis`, `mybatis-cdi` を追加

``` build.gradle
    // Source: https://mvnrepository.com/artifact/org.mybatis/mybatis
    implementation 'org.mybatis:mybatis:3.5.19'
    // Source: https://mvnrepository.com/artifact/org.mybatis/mybatis-cdi
    implementation 'org.mybatis:mybatis-cdi:2.1.1'
    
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation 'org.slf4j:slf4j-api:2.0.17'
    // Source: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation 'ch.qos.logback:logback-classic:1.5.32'
```

## 設定関係

`beans.xml`に以下を追記してMyBatisのインターセプターをCDIに管理させる

``` xml
<interceptors>
    <class>org.mybatis.cdi.JtaTransactionInterceptor</class>
</interceptors>
```
