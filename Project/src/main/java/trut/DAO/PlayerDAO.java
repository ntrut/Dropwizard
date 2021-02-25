package trut.DAO;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import trut.DB.playerMapper;
import trut.api.Player;

public class PlayerDAO 
{
	/*Returns all of the player objects*/
	public List<Player> getPlayers()
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players").map(new playerMapper()).list());
		return players;
	}
	
	/*Returns a single player object*/
	public Player getPlayer(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		try
		{
			handle = jdbi.open();
			Player player =  handle.createQuery("select * from players where id = '"+id+"'").map(new playerMapper()).one();
			return player;
			
		}finally 
		{
			if(handle != null)
				handle.close();
		}
		
	}
	
	/*create a player*/
	public void createPlayer(Player player)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		handle = jdbi.open();
		
		handle.execute("INSERT INTO players (id,name,appearances,goals,assits,yellow_cards,red_cards,cleansheets,team_id,league_id)" + 
				"VALUES('" + player.getId() + "' , '"+player.getName()+ "' , '"+player.getAppearances()+ "' ,'"+player.getGoals()+"',"
						+ " '"+player.getAssits()+"' , '"+ player.getYellow_cards()+"' ,'"+ player.getRed_cards()+"' ,'"+ player.getCleansheets()+"' , '"+ player.getTeam_id()+"', '"+ player.getLeague_id()+"')");
		handle.close();
	}
	
	/*DELETE A player*/
	public void deletePlayer(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		Handle handle = jdbi.open();
		
		handle.execute("DELETE FROM players WHERE id = '"+id+"'");
		
		handle.close();
	}
	
	/*UPDATE AN player*/
	public void updatePlayer(Player player, int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		handle = jdbi.open();
		
		handle.createUpdate("UPDATE players SET id='"+player.getId()+"', name = '"+player.getName()+"',appearances = '"+player.getAppearances()+"',"
				+ "goals = '"+player.getGoals()+"',assits = '"+player.getAssits()+"',yellow_cards = '"+player.getYellow_cards()+"',"
						+ "red_cards = '"+player.getRed_cards()+"',"+ "cleansheets = '"+player.getCleansheets()+"',"
								+ "team_id = '"+player.getTeam_id()+"',league_id = '"+player.getLeague_id()+"' WHERE id ='"+id+"'").execute();
		handle.close();
	}
}
