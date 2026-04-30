package epsilongtmyon.app.rest.sandbox02.bl;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import epsilongtmyon.app.common.db.entity.AppLog;
import epsilongtmyon.app.common.db.mapper.AppLogMapper;
import epsilongtmyon.app.rest.sandbox02.bl.param.SearchResult;

@ApplicationScoped
@Transactional
public class Sandbox02Service {

	@Inject
	private AppLogMapper appLogMapper;

	public SearchResult search() {
		List<AppLog> logs = appLogMapper.selectAll();

		SearchResult result = SearchResult.fromAppLogs(logs);

		return result;

	}

}
