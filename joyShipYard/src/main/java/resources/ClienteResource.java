package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/cliente")
public class ClienteResource {
	
	@GET
	@Path("/hello")
	public Response  helloWorld() {
		return Response.ok("Hello World").build();
	}
}
