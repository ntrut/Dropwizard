package trut.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import trut.api.Player;

public class playerMapper implements RowMapper<Player>
{

	@Override
	public Player map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		return new Player(rs.getInt("id"), rs.getString("name"),rs.getInt("appearances"),rs.getInt("goals"),rs.getInt("assits"),
			rs.getInt("yellow_cards"),rs.getInt("red_cards"),rs.getInt("cleansheets"),rs.getInt("team_id"),rs.getInt("league_id"));
	}

}
