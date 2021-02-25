package trut.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile 
{
	private int id;
	private String username;
	private String lastname;
	private String favTeam;
	private String age;
	
	
	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profile(int id, String username, String lastname, String favTeam, String age) {
		super();
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.favTeam = favTeam;
		this.age = age;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonProperty
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@JsonProperty
	public String getFavTeam() {
		return favTeam;
	}
	public void setFavTeam(String favTeam) {
		this.favTeam = favTeam;
	}
	@JsonProperty
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	
}
