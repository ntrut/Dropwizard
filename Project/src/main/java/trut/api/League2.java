package trut.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class League2 
{
	protected String name;
	protected int id;
	protected int numTeams;
	
	public League2()
	{
		
	}
	
	//without teams
	public League2(String name, int id, int numTeams) 
	{
		super();
		this.name = name;
		this.id = id;
		this.numTeams = numTeams;
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
	
}
