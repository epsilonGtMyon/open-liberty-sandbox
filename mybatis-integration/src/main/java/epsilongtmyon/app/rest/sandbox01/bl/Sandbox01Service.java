package epsilongtmyon.app.rest.sandbox01.bl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import epsilongtmyon.app.common.db.entity.AppLog;
import epsilongtmyon.app.common.db.mapper.AppLogMapper;

@ApplicationScoped
@Transactional
public class Sandbox01Service {

	@Inject
	private AppLogMapper appLogMapper;

	public void registerLog(String message, boolean throwException) {
		AppLog appLog = new AppLog();
		appLog.setLogMessage(message);

		appLogMapper.insert(appLog);

		if (throwException) {
			throw new RuntimeException("test rollback");
		}
	}

}
