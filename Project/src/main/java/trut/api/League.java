package trut.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class League 
{
	protected String name;
	protected int id;
	protected int numTeams;
	protected List<STeam> teams;
	
	public League()
	{
		
	}
	
	//without teams
	public League(String name, int id, int numTeams) 
	{
		super();
		this.name = name;
		this.id = id;
		this.numTeams = numTeams;
	}
	
	
	//with teams
	public League(String name, int id, int numTeams, List<STeam> teams) {
		super();
		this.name = name;
		this.id = id;
		this.numTeams = numTeams;
		this.teams = teams;
	}

	@JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonProperty
	public int getNumTeams() {
		return numTeams;
	}
	public void setNumTeams(int numTeams) {
		this.numTeams = numTeams;
	}
	
	@JsonProperty
	public List<STeam> getTeams() {
		return teams;
	}
	public void setTeams(List<STeam> teams2) {
		this.teams = teams2;
	}
	
	
}
