package trut.DAO;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import trut.api.User;

public class SigninDAO 
{

	/*Check if a user logged in*/
	public boolean checkLogin()
	{
		
		
		
		return false;
	}
	
	public void createUser(User user)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		handle = jdbi.open();
		
		handle.execute("INSERT INTO users (username,password)" + 
				"VALUES('"+user.getUsername()+ "', '"+user.getPassword()+ "')");
		handle.close();
		
	}
	
	/*IF TRUE THAT USER IS LOGGED IN 
	 * IF FALSE USER ISNT LOGGED IN*/
	public boolean loggedin(String username, String password)
	{

		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		try
		{
			handle = jdbi.open();
			
			Integer checkName = 0;
			try {
				checkName = handle.createQuery("select exists(select * from users where username='"+username+"' AND password='"+password+"')").mapTo(Integer.class).first();
				
				
			}catch(IllegalStateException e)
			{
				e.printStackTrace();
			}
					
			
			if(checkName == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}finally
		{
			if(handle != null)
				handle.close();
		}
	
	}
}

