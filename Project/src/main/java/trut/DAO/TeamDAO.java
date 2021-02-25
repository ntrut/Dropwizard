package trut.DAO;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import trut.DB.TeamMapper;
import trut.DB.playerMapper;
import trut.api.Player;
import trut.api.STeam;

public class TeamDAO 
{
	/*Returns all of the teams objects*/
	public List<STeam> getTeams()
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		List<STeam> teams = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM teams").map(new TeamMapper()).list());
		
		Handle handle = null;
		handle = jdbi.open();
		for(int i = 0; i < teams.size();i++)
		{
			
			List<Player> players = handle.createQuery("select * from players where team_id = '"+teams.get(i).getId()+"'").mapToBean(Player.class).list();
			teams.get(i).setPlayer(players);
			
		}
		
		return teams;
	}
	
	/*Return one team*/
	public STeam getTeam(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		/*Get the team from that id*/
		handle = jdbi.open();
		STeam team =  handle.createQuery("select * from teams where id = '"+id+"'").map(new TeamMapper()).one();
			
		/*Get all the players with that team_id*/
		List<Player> players = handle.createQuery("select * from players where team_id = '"+id+"'").map(new playerMapper()).list();
		team.setPlayer(players);
		handle.close();
			
		return team;
	}
	
	/*Return all players with same team_id*/
	public List<Player> getAllPlayers(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		handle = jdbi.open();
		
		List<Player> players = handle.createQuery("select * from players where team_id = '"+id+"'").map(new playerMapper()).list();
		
		return players;
		
	}
	
	/*Create a team*/
	public void createTeam(STeam team)
	{
		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		
		handle = jdbi.open();
		
		handle.execute("INSERT INTO teams (id,name,founded,matchesPlayed,wins,loses,goals,goalsConceded,GoalsperMatch,GoalsConcededperMatch,leaguePosition,league_id)" + 
				"VALUES('" + team.getId() + "' , '"+team.getName()+ "' , '"+team.getFounded()+ "' , '"+team.getMatchesPlayed()+"' , "
				+ "'"+ team.getWins()+"' , '"+ team.getLoses()+"', '"+team.getGoals()+ "' ,'"+team.getGoalsconceded()+"', "
						+ "'"+team.getGoalsperMatch()+"', '"+team.getGoalsConcededperMatch()+"', '"+team.getLeagueposition()+"', '"+team.getLeague_id()+"')");
		handle.close();
	}
	
	/*DELETE TEAM*/
	public void deleteTeam(int id)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		
		Handle handle = jdbi.open();
		
		handle.execute("DELETE FROM teams WHERE id = '"+id+"'");
		
		handle.close();
		
	}
	
	/*UPDATE TEAM*/
	public void updateTeam(int id, STeam team)
	{
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/homework", "root", "linuxserver");
		Handle handle = null;
		handle = jdbi.open();
		
		handle.createUpdate("UPDATE teams SET id='"+team.getId()+"',name = '"+team.getName()+"',founded = '"+team.getFounded()+"',"
				+ "matchesPlayed = '"+team.getMatchesPlayed()+"',wins = '"+team.getWins()+"',"
						+ "loses = '"+team.getLoses()+"',goals = '"+team.getGoals()+"' "
								+ ",goalsConceded = '"+team.getGoalsconceded()+"',GoalsperMatch = '"+team.getGoalsperMatch()+"',"
										+ "GoalsConcededperMatch = '"+team.getGoalsConcededperMatch()+"',leaguePosition = '"+team.getLeagueposition()+"'"
												+ ",league_id = '"+team.getLeague_id()+"' WHERE id ='"+id+"'").execute();
		handle.close();
		
	}
	
}
