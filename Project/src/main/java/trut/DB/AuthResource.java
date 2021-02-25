package trut.DB;

import java.net.URI;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.jersey.sessions.Session;
import trut.DAO.SigninDAO;
import trut.api.User;

@Path("/Project")
public class AuthResource 
{

	@POST
	@Path("/createuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		SigninDAO dao = new SigninDAO();
		
		try
		{
			handle = jdbi.open();
			
			/*Check if an ID aleady exists*/;
			Integer checkName = 0;
			try {
				checkName = handle.createQuery("select exists(select * from users where username='"+user.getUsername()+"')").mapTo(Integer.class).first();

			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
					
			/*If check == 1 then ID already exists so dont create the user
			 * else create the user if check == 0*/
			if(checkName == 1)
			{
				return Response.status(409, "Username already exists").build();
			}
			else
			{
					dao.createUser(user);
					return Response.ok(200).build();
			}
			
		}finally
		{
			if(handle != null)
				handle.close();
		}
	}
	

	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Signin(User user)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		try
		{
			handle = jdbi.open();
			
			/*Check if an ID aleady exists*/
			Integer checkName = 0;
			try {
				checkName = handle.createQuery("select exists(select * from users where username='"+user.getUsername()+"' AND password='"+user.getPassword()+"')").mapTo(Integer.class).first();
				
				
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
					
			/*If check == 1 then ID already exists so dont create the user
			 * else create the user if check == 0*/
			if(checkName == 0)
			{
				return Response.status(401, "Username or Password doesnt exist, Go to /Project/createuser to create an account").build();
			}
			else
			{
				URI uri = UriBuilder.fromUri("/Project/Authentication").build();
				return Response.seeOther(uri).build();
			}
			
		}finally
		{
			if(handle != null)
				handle.close();
		}
	}
	
	@GET
	@Path("/Authentication")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Authenicated(@Session HttpSession session)
	{
		String success = "success";
		session.setAttribute("success", success);
		return Response.ok(200).build();
	}
	
	
	
}
