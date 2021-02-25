package trut.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User 
{
	protected String Username;
	protected String Password;
	
	public User()
	{
		
	}
	
	public User(String username, String password) {
		super();
		this.Username = username;
		this.Password = password;
	}
	
	@JsonProperty
	public String getUsername() {
		return Username;
	}
	
	@JsonProperty
	public void setUsername(String username) {
		Username = username;
	}
	
	@JsonProperty
	public String getPassword() {
		return Password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
