package epsilongtmyon.app.common.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Collection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;
import org.slf4j.Logger;

import epsilongtmyon.app.common.db.mapper.AppLogMapper;
import epsilongtmyon.app.common.db.plugin.CommonFieldInterceptor;
import epsilongtmyon.app.common.db.plugin.LoggingInterceptor;

@Dependent // ←ないと動かん..
public class SqlSessionFactoryProvider {

	@Inject
	Logger logger;

	@Produces
	@ApplicationScoped
	@SessionFactoryProvider
	public SqlSessionFactory produceFactory() {
		try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

			Configuration configuration = sqlSessionFactory.getConfiguration();
			configuration.addInterceptor(new LoggingInterceptor());
			configuration.addInterceptor(new CommonFieldInterceptor());
			
			// Mapperは自動では認識されないのでxmlやコードで追加する必要がある。
			configuration.addMapper(AppLogMapper.class);

			Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
			logger.info("mappers = {}", mappers);

			return sqlSessionFactory;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
