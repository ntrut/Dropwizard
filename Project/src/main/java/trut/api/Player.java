package trut.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player 
{
	protected int id;
	protected String name;
	protected int appearances;
	protected int goals;
	protected int assits;
	protected int cleansheets;
	protected int yellow_cards;
	protected int red_cards;
	protected int team_id;
	protected int league_id;
	
	public Player()
	{
		
	}
	
	public Player(int id, String name,int appearances, int goals, int assits,  int yellow_cards, int red_cards,int cleansheets, int team_id, int league_id) {
		super();
		this.id = id;
		this.name = name;
		this.appearances = appearances;
		this.goals = goals;
		this.assits = assits;
		this.cleansheets = cleansheets;
		this.yellow_cards = yellow_cards;
		this.red_cards = red_cards;
		this.team_id = team_id;
		this.league_id = league_id;
	}
	
	@JsonProperty
	public int getAppearances() {
		return appearances;
	}

	public void setAppearances(int appearances) {
		this.appearances = appearances;
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
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	
	@JsonProperty
	public int getAssits() {
		return assits;
	}
	public void setAssits(int assits) {
		this.assits = assits;
	}
	
	@JsonProperty
	public int getCleansheets() {
		return cleansheets;
	}
	public void setCleansheets(int cleansheets) {
		this.cleansheets = cleansheets;
	}
	
	@JsonProperty
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getLeague_id() {
		return league_id;
	}

	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}

	public int getYellow_cards() {
		return yellow_cards;
	}

	public void setYellow_cards(int yellow_cards) {
		this.yellow_cards = yellow_cards;
	}

	public int getRed_cards() {
		return red_cards;
	}

	public void setRed_cards(int red_cards) {
		this.red_cards = red_cards;
	}
	
	
	
	
}
