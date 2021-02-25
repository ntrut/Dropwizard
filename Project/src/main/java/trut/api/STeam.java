package trut.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class STeam 
{
	protected int id;
	protected String name;
	protected int founded;
	protected int matchesPlayed;
	protected int wins;
	protected int loses;
	protected int goals;
	protected int goalsconceded;
	protected double GoalsperMatch;
	protected double GoalsConcededperMatch;
	protected int leagueposition;
	protected int league_id;
	protected List<Player> player;
	
	
	
	public STeam()
	{
		
	}
	
	//constructor without player array
	public STeam(int id, String name, int founded, int matchesPlayed, int wins, int loses, int goals, int goalsconceded,
		double goalsperMatch, double goalsConcededperMatch,int leagueposition, int league_id) {
		super();
		this.id = id;
		this.name = name;
		this.founded = founded;
		this.matchesPlayed = matchesPlayed;
		this.wins = wins;
		this.loses = loses;
		this.goals = goals;
		this.goalsconceded = goalsconceded;
		this.GoalsperMatch = goalsperMatch;
		this.GoalsConcededperMatch = goalsConcededperMatch;
		this.league_id = league_id;
		this.leagueposition = leagueposition;
	}
	
	
	//constructor with player array
	public STeam(int id, String name, int founded, int matchesPlayed, int wins, int loses, int goals, int goalsconceded,
			double goalsperMatch, double goalsConcededperMatch, int leagueposition, int league_id, List<Player> player) {
		super();
		this.id = id;
		this.name = name;
		this.founded = founded;
		this.matchesPlayed = matchesPlayed;
		this.wins = wins;
		this.loses = loses;
		this.goals = goals;
		this.goalsconceded = goalsconceded;
		this.GoalsperMatch = goalsperMatch;
		this.GoalsConcededperMatch = goalsConcededperMatch;
		this.league_id = league_id;
		this.leagueposition = leagueposition;
		this.player = player;
	}

	@JsonProperty
	public int getLeagueposition() {
		return leagueposition;
	}

	public void setLeagueposition(int leagueposition) {
		this.leagueposition = leagueposition;
	}

	@JsonProperty
	public int getLeague_id() {
		return league_id;
	}

	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}

	@JsonProperty
	public int getMatchesPlayed() {
		return matchesPlayed;
	}
	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}
	
	@JsonProperty
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	@JsonProperty
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	
	@JsonProperty
	public int getGoalsconceded() {
		return goalsconceded;
	}
	public void setGoalsconceded(int goalsconceded) {
		this.goalsconceded = goalsconceded;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty
	public int getFounded() {
		return founded;
	}
	public void setFounded(int founded) {
		this.founded = founded;
	}
	
	@JsonProperty
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	
	@JsonProperty
	public double getGoalsperMatch() {
		return GoalsperMatch;
	}
	public void setGoalsperMatch(double goalsperMatch) {
		GoalsperMatch = goalsperMatch;
	}
	
	@JsonProperty
	public double getGoalsConcededperMatch() {
		return GoalsConcededperMatch;
	}
	public void setGoalsConcededperMatch(double goalsConcededperMatch) {
		GoalsConcededperMatch = goalsConcededperMatch;
	}
	
	@JsonProperty
	public List<Player> getPlayer() {
		return player;
	}
	public void setPlayer(List<Player> players) {
		this.player = players;
	}
	
	
	
	
	
}
