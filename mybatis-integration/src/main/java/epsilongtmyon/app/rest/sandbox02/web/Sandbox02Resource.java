package epsilongtmyon.app.rest.sandbox02.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import epsilongtmyon.app.rest.sandbox02.bl.Sandbox02Service;
import epsilongtmyon.app.rest.sandbox02.bl.param.SearchResult;
import epsilongtmyon.app.rest.sandbox02.web.spec.SearchResponse;

@Path("/sandbox02")
@ApplicationScoped
public class Sandbox02Resource {

	@Inject
	private Sandbox02Service service;

	@GET
	@Path("search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response search() {
		final SearchResult searchResult = service.search();
		
		final SearchResponse searchResponse = SearchResponse.fromSearchResult(searchResult);

		return Response.ok(searchResponse).build();
	}

}
