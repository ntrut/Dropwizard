package trut.DAO;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;


import trut.DB.ProfileMapper;
import trut.DB.TeamMapper;
import trut.api.Player;
import trut.api.Profile;
import trut.api.STeam;

public class ProfileDAO 
{

	/*Returns all of the profile objects*/
	public List<Profile> getProfiles()
	{
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Profile> profiles = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM profile").map(new ProfileMapper()).list());
		return profiles;
	}
	
	/*Returns a single profile object*/
	public Profile getProfile(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		try
		{
			handle = jdbi.open();
			Profile profiles =  handle.createQuery("select * from profile where id = '"+id+"'").map(new ProfileMapper()).one();
			return profiles;
			
		}finally 
		{
			if(handle != null)
				handle.close();
		}
		
	}
	
	/*Get the profile favorite team*/
	public STeam getProfileTeam(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		try
		{
			 handle = jdbi.open();
			/*get the string favteam from the profile*/
			Profile profiles =  handle.createQuery("select * from profile where id = '"+id+"'").map(new ProfileMapper()).one();
			
			String s = profiles.getFavTeam();
			
			/*Create the team*/
			STeam teams =  handle.createQuery("select * from teams where name = '"+s+"'").map(new TeamMapper()).one();
			
			/*Get All the players that have the team_id == to id to that one team*/
			List<Player> players = handle.createQuery("select * from players where team_id = '"+teams.getId()+"'").mapToBean(Player.class).list();
			teams.setPlayer(players);
			return teams;
			
			
		}finally 
		{
			if(handle != null)
				handle.close();
		}
		
	}
	
	/*create a profile*/
	public void createProfile(Profile newp)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		handle = jdbi.open();
		
		handle.execute("INSERT INTO profile (id,name,lastname,age,favTeam)" + 
				"VALUES('" + newp.getId() + "' , '"+newp.getUsername()+ "' , '"+newp.getLastname()+"', '"+newp.getAge()+"' , '"+newp.getFavTeam()+"')");
		handle.close();
	}
	
	/*DELETE A PROFILE*/
	public void deleteProfile(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		Handle handle = jdbi.open();
		
		handle.execute("DELETE FROM profile WHERE id = '"+id+"'");
		handle.close();
	}
	
	/*UPDATE AN OBJECT*/
	public void updateProfile(Profile newp, int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		handle = jdbi.open();
		
		handle.createUpdate("UPDATE profile SET id='"+newp.getId()+"', name = '"+newp.getUsername()+"',lastname = '"+newp.getLastname()+"',"
				+ "age = '"+newp.getAge()+"',favTeam = '"+newp.getFavTeam()+"' WHERE id ='"+id+"'").execute();
		handle.close();
	}
}
