package epsilongtmyon.app.rest.sandbox01.bl;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.mybatis.cdi.Transactional;
import org.slf4j.Logger;

import epsilongtmyon.app.common.db.entity.AppLog;
import epsilongtmyon.app.common.db.mapper.AppLogMapper;
import epsilongtmyon.app.common.db.mapper.AppLogMapper2;

@ApplicationScoped
@Transactional
public class Sandbox01Service {

	@Inject
	Logger logger;

	@Inject
	private AppLogMapper appLogMapper;

	@Inject
	private AppLogMapper2 appLogMapper2;

	public void registerLog(String message, boolean throwException) {
		AppLog appLog = new AppLog();
		appLog.setLogMessage(message);

		appLogMapper.insert(appLog);

		AppLog lastLog = appLogMapper2.selectLast();
		logger.info(Objects.toString(lastLog));

		if (throwException) {
			throw new RuntimeException("test rollback");
		}
	}

}
