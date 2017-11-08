package br.inatel.projetofinal;

import java.util.List;

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


@Path("/servuser")
public class UsuarioService {

	
		@Context
		private UriInfo uriInfo;
		
		@GET
		@Path("/testU")
		@Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
		public Response getTeste( ) {
			String test = "hello world!";
			GenericEntity<String> entity = new GenericEntity<String>(test) {};
			
			return Response.status(Status.OK).entity(entity).build();
		}
		
		@Path("/alluser")
		@GET
		@Produces({MediaType.APPLICATION_JSON})
		public Response getUsuario() {
			try {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				List<Usuario> usuarios = usuarioDAO.list();
				GenericEntity<List<Usuario>> entities = new GenericEntity<List<Usuario>>(usuarios) {};
				return Response
						.status(Status.OK)
						.entity(entities)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Status.NOT_FOUND).build();
			}
		}
		/*@Path("/adduser")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces({MediaType.APPLICATION_JSON})
		public Response createUsuario(Usuario usuario) {
			UsuarioDAO usuarioDAO;
			try {
				usuarioDAO = new UsuarioDAO();
				Usuario newUsuario = usuarioDAO.insertUsuario(usuario);
				GenericEntity entity = new GenericEntity<Usuario>(newUsuario) {};
				return Response
						.status(Status.CREATED)
						.header("Location", String.format("%s/%s",uriInfo.getAbsolutePath().toString(),newUsuario.getId()))
						.entity(entity)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		}*/
	}

