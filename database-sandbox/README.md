# database-sandbox

OpenLibertyにデータソース定義を試す。


[Relational database connections with JDBC](https://openliberty.io/docs/latest/relational-database-connections-JDBC.html) を参考にする。

## build.gradleの修正

```
configurations {
  jdbcLib
}
dependencies {
  jdbcLib 'com.h2database:h2:2.4.240'
}
task copyJDBC(type: Copy) {
  from configurations.jdbcLib
  into "$buildDir/wlp/usr/servers/defaultServer/jdbc"
  include '*.jar'
}
deploy.dependsOn 'copyJDBC'
```

上記のようにしconfigurationsにjdbcLibを追加してH2を追加する。
これをopenLiberty配下のディレクトリにコピーするようにしておく。

## server.xmlの修正

``` xml
	<library id="jdbcLib">
	    <fileset dir="jdbc" includes="*.jar"/>
	</library>


	<dataSource jndiName="jdbc/myDB" type="javax.sql.DataSource">
	    <jdbcDriver libraryRef="jdbcLib"/>
	    <properties URL="jdbc:h2:tcp://localhost/test"
	                user="sa"
	                password=""/>
	</dataSource>
```

JDBCドライバを配置するディレクトリを定義し データソースを定義
dataSourceの設定については [Liberty でのリレーショナル・データベース接続の構成](https://www.ibm.com/docs/ja/was-liberty/nd?topic=liberty-configuring-relational-database-connectivity-in) を参照