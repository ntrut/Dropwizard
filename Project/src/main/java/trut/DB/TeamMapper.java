package trut.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import trut.api.STeam;

public class TeamMapper implements RowMapper<STeam>
{

	@Override
	public STeam map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		return new STeam(rs.getInt("id"), rs.getString("name"),rs.getInt("founded"),rs.getInt("matchesPlayed"),rs.getInt("wins"), rs.getInt("loses"), rs.getInt("goals"), 
				rs.getInt("goalsConceded"), rs.getDouble("GoalsperMatch"), rs.getDouble("GoalsConcededperMatch"), rs.getInt("leaguePosition"), rs.getInt("league_id"));
	}

}
