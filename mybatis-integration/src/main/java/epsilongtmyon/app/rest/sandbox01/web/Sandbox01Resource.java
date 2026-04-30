package epsilongtmyon.app.rest.sandbox01.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import epsilongtmyon.app.rest.sandbox01.bl.Sandbox01Service;
import epsilongtmyon.app.rest.sandbox01.web.spec.RegisterLogRequest;

@Path("/sandbox01")
@ApplicationScoped
public class Sandbox01Resource {

	@Inject
	private Sandbox01Service service;

	@POST
	@Path("registerLog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerLog(RegisterLogRequest request) {

		String message = request.getMessage();
		boolean throwException = Boolean.parseBoolean(request.getThrowException());

		service.registerLog(message, throwException);

		return Response.ok().build();
	}
}
