package trut.DAO;

import java.util.HashMap;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import trut.DB.League2Mapper;
import trut.DB.TeamMapper;
import trut.DB.leagueMapper;
import trut.DB.playerMapper;
import trut.api.League;
import trut.api.League2;
import trut.api.Player;
import trut.api.STeam;


public class LeagueDAO 
{
	/*Returns all of the leagues objects*/
	public List<League2> getLeagues()
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<League2> League = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM Leagues").map(new League2Mapper()).list());
		return League;
	}
	
	/*Return one league with all of the teams and players*/
	public League getLeague(int id)
	{
		
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		League league = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM Leagues where id='"+id+"'").map(new leagueMapper()).one());
		
		/*Get the teams with the players*/
		List<STeam> teams = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM teams where league_id = '"+id+"'").map(new TeamMapper()).list());
		
		Handle handle = null;
		handle = jdbi.open();
		for(int i = 0; i < teams.size();i++)
		{
			
			List<Player> players = handle.createQuery("select * from players where team_id = '"+teams.get(i).getId()+"'").mapToBean(Player.class).list();
			teams.get(i).setPlayer(players);
			
		}
		league.setTeams(teams);
		
		return league;
	}
	
	/*Create a league*/
	public void createLeague(League league)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		handle = jdbi.open();
		
		handle.execute("INSERT INTO Leagues (id,name,numTeams)" + "VALUES('" + league.getId() + "' , '"+league.getName()+ "', '"+league.getNumTeams()+ "')");
		handle.close();
	}
	
	/*Delete league*/
	public void deleteLeague(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		Handle handle = jdbi.open();
		
		handle.execute("DELETE FROM Leagues WHERE id = '"+id+"'");
		
		handle.close();
	}
	
	/*update a team*/
	public void updateLeague(int id, League league)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		handle = jdbi.open();
		
		handle.createUpdate("UPDATE Leagues SET id='"+league.getId()+"', name = '"+league.getName()+"', numTeams='"+league.getNumTeams()+"' WHERE id ='"+id+"'").execute();
		handle.close();
		
	}
	
	/*CREATE A FUNCTION THAT RETURNS ALL PLAYERS FROM THAT LEAGUE*/
	public List<Player> getLeaguePlayer(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		return players;
	}
	
	/*CREATE A FUNCTION THAT RETURNS THE TOP 5 GOAL SCORERS*/
	public HashMap<Integer, Player> getTopScorers(int id) 
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		 HashMap<Integer, Player> playerHASH  = new HashMap<Integer, Player>();
		
		/*array of top 5 goal scorers*/
		int counter = 0;
		int maxTop = 5;
		int remove = 0;
		int number = 1;
		
		/*IF there are less than 5 players in the list of players
		 * make sure the counter is the number of the size of the players list*/
		if(players.size() < 5)
		{
			maxTop = players.size();
		}
		
		while(counter != maxTop)
		{
			int test = 0;
			Player temp = players.get(0);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getGoals() > temp.getGoals())
				{
					temp = players.get(i);
					remove = i;
					test++;
				}
			}
			
			/*IF WE FOUND TOP ASSITS THEN WE REMOVE THAT PLAYER SO
			 * THAT WE DONT HAVE TO COMPARE HIM AGAAIN TO OTHER PLAYERS
			 * IF INDEX 0 WAS THE BIGGEST THEN TEST IS 0 SO WE REMOVE INDEX 0*/
			if(test == 0)
			{
				players.remove(test);
			}
			else
				players.remove(remove);
			
			if(temp.getGoals() != 0)
			{
				playerHASH.put(number++, temp);
			}
			
			counter++;
		}
		
		return playerHASH;
	}
	
	/*CREATE A FUNCTION THAT RETURNS THE TOP 5 ASSISTORS*/
	public HashMap<Integer, Player> getTopAssits(int id) 
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		 HashMap<Integer, Player> playerHASH  = new HashMap<Integer, Player>();
		
		/*array of top 5 goal scorers*/
		int counter = 0;
		int maxTop = 5;
		int remove = 0;
		int number = 1;
		
		/*IF there are less than 5 players in the list of players
		 * make sure the counter is the number of the size of the players list*/
		if(players.size() < 5)
		{
			maxTop = players.size();
		}
		
		while(counter != maxTop)
		{
			int test = 0;
			Player temp = players.get(0);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getAssits() > temp.getAssits())
				{
					temp = players.get(i);
					remove = i;
					test++;
				}
			}
			
			/*IF WE FOUND TOP ASSITS THEN WE REMOVE THAT PLAYER SO
			 * THAT WE DONT HAVE TO COMPARE HIM AGAAIN TO OTHER PLAYERS*/
			if(test == 0)
			{
				players.remove(test);
			}
			else
				players.remove(remove);
			
			if(temp.getAssits() != 0)
			{
				playerHASH.put(number++, temp);
			}
			
			counter++;
		}
		
		return playerHASH;
	}
	
	/*ADD FUNCTION FOR TOP 5 YELLOW CARDS*/
	public HashMap<Integer, Player> getTopYellow(int id) 
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		 HashMap<Integer, Player> playerHASH  = new HashMap<Integer, Player>();
		
		
		int counter = 0;
		int maxTop = 5;
		int remove = 0;
		int number = 1;
		
		/*IF there are less than 5 players in the list of players
		 * make sure the counter is the number of the size of the players list*/
		if(players.size() < 5)
		{
			maxTop = players.size();
		}
		
		while(counter != maxTop)
		{
			int test = 0;
			Player temp = players.get(0);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getYellow_cards() > temp.getYellow_cards())
				{
					temp = players.get(i);
					remove = i;
					test++;
				}
			}
			
			/*IF WE FOUND TOP YELLOW CARDS THEN WE REMOVE THAT PLAYER SO
			 * THAT WE DONT HAVE TO COMPARE HIM AGAAIN TO OTHER PLAYERS*/
			if(test == 0)
			{
				players.remove(test);
			}
			else
				players.remove(remove);
			
			/*WE DONT WANT PLAYERS THAT HAVE 0 YELLOW CARDS ON OUR TOP 5
			 * WE DONT SHOW PLAYERS WITH 0 YELLOW CARDS*/
			if(temp.getYellow_cards() != 0)
			{
				playerHASH.put(number++, temp);
			}
			
			counter++;
		}
		
		return playerHASH;
	}
	/*ADD FUNCTIONS FOR RED CARDS*/
	public HashMap<Integer, Player> getTopRed(int id) 
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		 HashMap<Integer, Player> playerHASH  = new HashMap<Integer, Player>();
		
		
		int counter = 0;
		int maxTop = 5;
		int remove = 0;
		int number = 1;
		
		/*IF there are less than 5 players in the list of players
		 * make sure the counter is the number of the size of the players list*/
		if(players.size() < 5)
		{
			maxTop = players.size();
		}
		
		while(counter != maxTop)
		{
			int test = 0;
			Player temp = players.get(0);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getRed_cards() > temp.getRed_cards())
				{
					temp = players.get(i);
					remove = i;
					test++;
				}
			}
			
			/*IF WE FOUND TOP RED CARDS THEN WE REMOVE THAT PLAYER SO
			 * THAT WE DONT HAVE TO COMPARE HIM AGAAIN TO OTHER PLAYERS*/
			if(test == 0)
			{
				players.remove(test);
			}
			else
				players.remove(remove);
			
			/*IF A PLAYER HAS 0 RED CARDS THEN WE DONT WANT TO SHOW IT
			 * WE ONLY SHOW PLAYERS WITH MORE THAN 0 RED CARDS*/
			if(temp.getRed_cards() != 0)
			{
				playerHASH.put(number++, temp);
			}
			
			counter++;
		}
		
		return playerHASH;
	}
	
	/*CREATE A FUNCTION THAT RETURNS THE TOP 5 CLEAN SHEETS*/
	public HashMap<Integer, Player> getTopCleansheets(int id) 
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		List<Player> players = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM players where league_id ='"+id+"'").map(new playerMapper()).list());
		 HashMap<Integer, Player> playerHASH  = new HashMap<Integer, Player>();
		
		
		int counter = 0;
		int maxTop = 5;
		int remove = 0;
		int number = 1;
		
		/*IF there are less than 5 players in the list of players
		 * make sure the counter is the number of the size of the players list*/
		if(players.size() < 5)
		{
			maxTop = players.size();
		}
		
		while(counter != maxTop)
		{
			int test = 0;
			Player temp = players.get(0);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getCleansheets() > temp.getCleansheets())
				{
					temp = players.get(i);
					remove = i;
					test++;
				}
			}
			
			/*IF WE FOUND TOP CLEANSHEETS THEN WE REMOVE THAT PLAYER SO
			 * THAT WE DONT HAVE TO COMPARE HIM AGAAIN TO OTHER PLAYERS*/
			if(test == 0)
			{
				players.remove(test);
			}
			else
				players.remove(remove);
			
			/*IF A PLAYER HAS 0 CLEANSHEETS THEN WE DONT WANT TO SHOW IT
			 * WE ONLY SHOW PLAYERS WITH MORE THAN 0 CLEANSHEETS*/
			if(temp.getCleansheets() != 0)
			{
				playerHASH.put(number++, temp);
			}
			
			counter++;
		}
		
		return playerHASH;
	}
}
