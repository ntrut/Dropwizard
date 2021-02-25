package trut.DB;

import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.jersey.sessions.Session;
import trut.DAO.PlayerDAO;
import trut.api.Player;



@Path("/Project")
public class playerResource 
{
	@Path("/player")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfilesss(@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			PlayerDAO dao = new PlayerDAO();
			
			return Response.ok(dao.getPlayers()).build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/player/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlayer(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			PlayerDAO dao = new PlayerDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
		
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from players where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getPlayer(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	@POST
	@Path("/player")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(Player newp,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			PlayerDAO dao = new PlayerDAO();
			
			try
			{
				handle = jdbi.open();
				
				/*Check if an ID aleady exists*/
				Integer check = 0;
				try {
					check = handle.createQuery("select exists(select * from players where id='"+newp.getId()+"')").mapTo(Integer.class).first();
				}catch(IllegalStateException e)
				{
					e.printStackTrace();
				}
						
				/*If check == 1 then ID already exists so dont create the player
				 * else create the player if check == 0*/
				if(check == 1)
				{
					return Response.status(409, "ID already exists").build();
				}
				else
				{
						dao.createPlayer(newp);
						return Response.ok(200).build();
				}
				
			}finally
			{
				if(handle != null)
					handle.close();
			}
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@DELETE
	@Path("/player/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProfile(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			PlayerDAO dao = new PlayerDAO();
			Handle handle = jdbi.open();
			
			/*Check if an id exists or not*/
			Integer check = 0;
			try {
				check = handle.createQuery("select exists(select * from players where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*IF ID exists then delete it*/
			if(check == 1)
			{
				dao.deletePlayer(id);
				return Response.ok(200).build();
			}
			else
				return  Response.status(404, "ID doesnt exists").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@PUT
	@Path("/player/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProfile(@PathParam("id") int id, Player newp,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			handle = jdbi.open();
			PlayerDAO dao = new PlayerDAO();
			
			
			/*See if my {id} exists*/
			Integer first = 0;
			try {
				first = handle.createQuery("select exists(select * from players where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*If first == 1 then {id} exists*/
			if(first == 1)
			{
				try
				{
					/*Check if the ID we are changing to already aleady exists*/
					Integer check = 0;
					try {
						check = handle.createQuery("select exists(select * from players where id='"+newp.getId()+"')").mapTo(Integer.class).first();
					}catch(IllegalStateException e)
					{
						e.printStackTrace();
					}
					
					if(check == 1 && newp.getId() != id)
					{
						return Response.status(409, "This Player ID already exists!").build();
					}
					else
					{
						/*UPDATE Player*/
						dao.updatePlayer(newp, id);		
						return Response.ok(200).build();
						
					}
				}finally
				{
					if(handle != null)
						handle.close();
				}
				
			}
			else
			{
				handle.close();
				return Response.status(404, "This Player ID doesn't exist!").build();
			}
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
}

