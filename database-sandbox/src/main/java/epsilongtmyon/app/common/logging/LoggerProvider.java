package epsilongtmyon.app.common.logging;

import java.util.logging.Logger;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

@Dependent
public class LoggerProvider {

	@Produces
	@Dependent
	public Logger logger(InjectionPoint point) {
		return Logger.getLogger(point.getMember().getDeclaringClass().getName());
	}
}
