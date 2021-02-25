package trut.DB;

import trut.DAO.ProfileDAO;

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
import trut.api.Profile;


@Path("/Project")
public class profileResource 
{
	
	@Path("/profile")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfilesss(@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			ProfileDAO dao = new ProfileDAO();
			
			return Response.ok(dao.getProfiles()).build();
			
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	
	@Path("/profile/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			ProfileDAO dao = new ProfileDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
		
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from profile where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if an exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getProfile(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/profile/{id}/favteam")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileTeam(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			ProfileDAO dao = new ProfileDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
		
			 handle = jdbi.open();
			
			 			
			 /*Make sure the id exists*/
			 Integer first = 0;
				try {
					handle = jdbi.open();
					first = handle.createQuery("select exists(select * from profile where id='"+id+"')").mapTo(Integer.class).first();
				}catch(IllegalStateException e)
				{
					e.printStackTrace();
				}
				
				/*if first == 1, then continue*/
				if(first == 1)
				{
					/*Get the favTeam string from that ID*/
					 Profile profiles =  handle.createQuery("select * from profile where id = '"+id+"'").map(new ProfileMapper()).one();
					 String s = profiles.getFavTeam();

					
					/*Now check if favteam string exists*/
					Integer second = 0;
					try {
						handle = jdbi.open();
						second = handle.createQuery("select exists(select * from teams where name='"+s+"')").mapTo(Integer.class).first();
					}catch(IllegalStateException e)
					{
						e.printStackTrace();
					}
					
					/*CHECK IF FAVTEAM EXISTS*/
					if(second == 1)
					{
						handle.close();
						return Response.ok(dao.getProfileTeam(id)).build();
					}
					else
					{
						handle.close();
						return Response.status(404, "Profiles favTeam doesnt exist!").build();
					}
					
				}
				else
					handle.close();
					return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	@POST
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(Profile newp,@Session HttpSession session)
	{
		
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			ProfileDAO dao = new ProfileDAO();
			
			try
			{
				handle = jdbi.open();
				
				/*Check if an ID aleady exists*/
				Integer check = 0;
				try {
					check = handle.createQuery("select exists(select * from profile where id='"+newp.getId()+"')").mapTo(Integer.class).first();
				}catch(IllegalStateException e)
				{
					e.printStackTrace();
				}
						
				/*If check == 1 then ID already exists so dont create the profile
				 * else create the profile if check == 0*/
				if(check == 1)
				{
					return Response.status(409, "ID already exists").build();
				}
				else
				{
						dao.createProfile(newp);
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
	@Path("/profile/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProfile(@PathParam("id") int id,@Session HttpSession session)
	{
		
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			ProfileDAO dao = new ProfileDAO();
			Handle handle = jdbi.open();
			
			/*Check if an id exists or not*/
			Integer check = 0;
			try {
				check = handle.createQuery("select exists(select * from profile where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			if(check == 1)
			{
				dao.deleteProfile(id);
				return Response.ok(200).build();
			}
			else
				return  Response.status(404, "ID doesnt exist").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in!").build();
	}
	
	@PUT
	@Path("/profile/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProfile(@PathParam("id") int id, Profile newp,@Session HttpSession session)
	{
		
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			handle = jdbi.open();
			ProfileDAO dao = new ProfileDAO();
			
			
			/*See if my {id} exists*/
			Integer first = 0;
			try {
				first = handle.createQuery("select exists(select * from profile where id='"+id+"')").mapTo(Integer.class).first();
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
						check = handle.createQuery("select exists(select * from profile where id='"+newp.getId()+"')").mapTo(Integer.class).first();
					}catch(IllegalStateException e)
					{
						e.printStackTrace();
					}
					
					if(check == 1 && newp.getId() != id)
					{
						return Response.status(409, "This profile ID already exists!").build();
					}
					else
					{
						/*UPDATE PROFILE*/
						dao.updateProfile(newp, id);		
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
				return Response.status(404, "This profile ID doesn't exist!").build();
			}
			
	
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
}
