package epsilongtmyon.rest.app.sandbox01.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import epsilongtmyon.rest.app.common.util.ObjectUtil;
import epsilongtmyon.rest.app.sandbox01.web.spec.Get1Request;
import epsilongtmyon.rest.app.sandbox01.web.spec.Get1Response;

@Path("/sandbox01")
@ApplicationScoped
public class Sandbox01Resource {

	@GET
	@Path("get1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get1(
			// まとめて一つのオブジェクトにしたいときは@BeanParam
			@BeanParam Get1Request request) {

		System.out.println(request);

		final String value01 = ObjectUtil.defaultValue(request.getValue01(), "");
		final String value02 = ObjectUtil.defaultValue(request.getValue02(), "");

		Get1Response response = new Get1Response();
		response.setValue1(value01 + ":" + value02);

		return Response.ok(response).build();
	}
}
