package epsilongtmyon.app.common.db.plugin;
import java.sql.Statement;
import java.util.Arrays;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;

@ApplicationScoped
@Intercepts({
	@Signature(type = StatementHandler.class, method = "update", args = { Statement.class })
	
})
public class LoggingInterceptor implements Interceptor {

	@Inject
	Logger logger;


	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		logger.info("begin");
		logger.info("target = {}", invocation.getTarget());
		logger.info("method = {}", invocation.getMethod());
		logger.info("args = {}", Arrays.toString(invocation.getArgs()));

		Object returnObject = invocation.proceed();
		logger.info("end {}", returnObject);

		return returnObject;
	}

}
