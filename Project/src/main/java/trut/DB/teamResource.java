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
import trut.DAO.TeamDAO;
import trut.api.STeam;

@Path("/Project")
public class teamResource 
{
	@Path("/team")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeams(@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			TeamDAO dao = new TeamDAO();
			
			return Response.ok(dao.getTeams()).build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/team/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeam(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			TeamDAO dao = new TeamDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
		
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from teams where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTeam(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/team/{id}/player")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeamPlayer(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			TeamDAO dao = new TeamDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from teams where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getAllPlayers(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@POST
	@Path("/team")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(STeam team,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			TeamDAO dao = new TeamDAO();
			
			try
			{
				handle = jdbi.open();
				
				/*Check if an ID aleady exists*/
				Integer check = 0;
				try {
					check = handle.createQuery("select exists(select * from teams where id='"+team.getId()+"')").mapTo(Integer.class).first();
				}catch(IllegalStateException e)
				{
					e.printStackTrace();
				}
						
				/*If check == 1 then ID already exists so dont create the team
				 * else create the team if check == 0*/
				if(check == 1)
				{
					return Response.status(409, "ID already exists").build();
				}
				else
				{
						dao.createTeam(team);
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
	
	@Path("/team/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeam(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			TeamDAO dao = new TeamDAO();
			Handle handle = jdbi.open();
			
			/*Check if an id exists or not*/
			Integer check = 0;
			try {
				check = handle.createQuery("select exists(select * from teams where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*IF ID exists then delete it*/
			if(check == 1)
			{
				dao.deleteTeam(id);
				return Response.ok(200).build();
			}
			else
				return  Response.status(404, "ID doesnt exists").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	@Path("/team/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTeam(@PathParam("id") int id, STeam team,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			handle = jdbi.open();
			TeamDAO dao = new TeamDAO();
			
			
			/*See if my {id} exists*/
			Integer first = 0;
			try {
				first = handle.createQuery("select exists(select * from teams where id='"+id+"')").mapTo(Integer.class).first();
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
						check = handle.createQuery("select exists(select * from teams where id='"+team.getId()+"')").mapTo(Integer.class).first();
					}catch(IllegalStateException e)
					{
						e.printStackTrace();
					}
					
					if(check == 1 && team.getId() != id)
					{
						return Response.status(409, "This Team ID already exists!").build();
					}
					else
					{
						/*UPDATE Team*/
						dao.updateTeam(id,team);		
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
				return Response.status(404, "This Team ID doesn't exist!").build();
			}
			
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
}