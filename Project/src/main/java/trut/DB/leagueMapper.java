package trut.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import trut.api.League;

public class leagueMapper implements RowMapper<League>
{

	@Override
	public League map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		return new League(rs.getString("name"),rs.getInt("id") ,rs.getInt("numTeams"));
	}

}
