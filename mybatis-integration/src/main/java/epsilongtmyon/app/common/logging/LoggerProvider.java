package epsilongtmyon.app.common.logging;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dependent
public class LoggerProvider {

	@Produces
	@Dependent
	public Logger logger(InjectionPoint point) {
		return LoggerFactory.getLogger(point.getMember().getDeclaringClass());
	}
}
