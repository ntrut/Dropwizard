package trut.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import trut.api.Profile;

public class ProfileMapper implements RowMapper<Profile>
{
	
	/*To get a specific id*/
	@Override
	public Profile map(ResultSet rs, StatementContext ctx) throws SQLException 
	{
		return new Profile(rs.getInt("id"), rs.getString("name"),rs.getString("lastname"), rs.getString("favTeam"),rs.getString("age"));
	}

}
