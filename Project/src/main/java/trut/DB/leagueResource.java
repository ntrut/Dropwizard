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
import trut.DAO.LeagueDAO;
import trut.api.League;

@Path("/Project")
public class leagueResource 
{
	@Path("/league")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeagues(@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			 
			return  Response.ok(dao.getLeagues()).build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/league/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeague(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
		
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getLeague(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
			
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@Path("/league/{id}/top5scorers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopGoalScorers(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTopScorers(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	@Path("/league/{id}/top5assits")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopAssists(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTopAssits(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	/*RETURNS THE TOP 5 PLAYERS THAT HAVE THE MOST RED CARDS*/
	@Path("/league/{id}/top5redcards")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopRedCards(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTopRed(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	/*RETURNS THE TOP 5 PLAYERS THAT HAVE THE MOST YELLOW CARDS*/
	@Path("/league/{id}/top5yellowcards")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopYellowCards(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTopYellow(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	/*RETURNS THE TOP 5 PLAYERS THAT HAVE THE MOST CLEANSHEETS*/
	@Path("/league/{id}/top5cleansheets")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopCleansheets(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getTopCleansheets(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	/*Gets all players from that league*/
	@Path("/league/{id}/players")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlayers(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			LeagueDAO dao = new LeagueDAO();
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			
			/*Check if the ID exist*/
			Integer first = 0;
			try {
				handle = jdbi.open();
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*Check if the ID exists or not*/
			if(first == 1)
			{
				handle.close();
				return Response.ok(dao.getLeaguePlayer(id)).build();
			}
			else
				handle.close();
				return Response.status(404, "ID doesnt exist!").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
	}
	
	@POST
	@Path("/league")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLeague(League league,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			LeagueDAO dao = new LeagueDAO();
			
			try
			{
				handle = jdbi.open();
				
				/*Check if an ID aleady exists*/
				Integer check = 0;
				try {
					check = handle.createQuery("select exists(select * from Leagues where id='"+league.getId()+"')").mapTo(Integer.class).first();
				}catch(IllegalStateException e)
				{
					e.printStackTrace();
				}
						
				/*If check == 1 then ID already exists so dont create the league
				 * else create the team if check == 0*/
				if(check == 1)
				{
					return Response.status(409, "ID already exists").build();
				}
				else
				{
						dao.createLeague(league);
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
	
	@Path("/league/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLeague(@PathParam("id") int id,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			LeagueDAO dao = new LeagueDAO();
			Handle handle = jdbi.open();
			
			/*Check if an id exists or not*/
			Integer check = 0;
			try {
				check = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
			
			/*IF ID exists then delete it*/
			if(check == 1)
			{
				dao.deleteLeague(id);
				return Response.ok(200).build();
			}
			else
				return  Response.status(404, "ID doesnt exists").build();
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
	@Path("/league/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTeam(@PathParam("id") int id, League league,@Session HttpSession session)
	{
		String test = Objects.toString(session.getAttribute("success"));
		
		if(test == "success")
		{
			Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
			Handle handle = null;
			handle = jdbi.open();
			LeagueDAO dao = new LeagueDAO();
			
			
			/*See if my {id} exists*/
			Integer first = 0;
			try {
				first = handle.createQuery("select exists(select * from Leagues where id='"+id+"')").mapTo(Integer.class).first();
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
						check = handle.createQuery("select exists(select * from Leagues where id='"+league.getId()+"')").mapTo(Integer.class).first();
					}catch(IllegalStateException e)
					{
						e.printStackTrace();
					}
					
					if(check == 1 && league.getId() != id)
					{
						return Response.status(409, "This League ID already exists!").build();
					}
					else
					{
						/*UPDATE Team*/
						dao.updateLeague(id,league);		
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
				return Response.status(404, "This League ID doesn't exist!").build();
			}
		}
		else
			return Response.status(401, "Your Unauthorized, go to /Project/signin to sign in !").build();
		
	}
	
}