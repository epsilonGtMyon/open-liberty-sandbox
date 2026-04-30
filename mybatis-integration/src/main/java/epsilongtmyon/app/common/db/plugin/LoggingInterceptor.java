package epsilongtmyon.app.common.db.plugin;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({
	@Signature(type = StatementHandler.class, method = "update", args = { Statement.class })
	
})
public class LoggingInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

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
