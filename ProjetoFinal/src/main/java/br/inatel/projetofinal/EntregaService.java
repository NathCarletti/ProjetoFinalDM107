package br.inatel.projetofinal;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;



@Path("/servE")
public class EntregaService {
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Path("/test")
	@Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
	public Response getTeste( ) {
		String test = "hello world!";
		GenericEntity<String> entity = new GenericEntity<String>(test) {};
		
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@Path("/entrega")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEntrega() {
		try {
			EntregaDAO entregaDAO = new EntregaDAO();
			List<Entrega> entregas = entregaDAO.list();
			GenericEntity<List<Entrega>> entities = new GenericEntity<List<Entrega>>(entregas) {};
			return Response
					.status(Status.OK)
					.entity(entities)
					.build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public Response createContato(Entrega entrega) {
		EntregaDAO entregaDAO;
		try {
			entregaDAO = new EntregaDAO();
			Entrega newEntrega = entregaDAO.insertEntrega(entrega);
			GenericEntity entity = new GenericEntity<Entrega>(newEntrega) {};
			return Response
					.status(Status.CREATED)
					.header("Location", String.format("%s/%s",uriInfo.getAbsolutePath().toString(),newEntrega.getId()))
					.entity(entity)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}

