package epsilongtmyon.app.common.db.provider;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

import epsilongtmyon.app.common.db.annotation.MyDB;

@Dependent
public class DataSourceProvider {

	@Resource(name = "jdbc/myDB")
	DataSource dataSource;

	// @Resourceでjdbc/myDBという文字列の入力をさせないようにするため
	// @ProducesをつかってCDIに管理させる。
	@Produces
	@MyDB
	@ApplicationScoped
	public DataSource myDataSource() {
		return dataSource;
	}
}
