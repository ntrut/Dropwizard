package trut.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import trut.api.League2;

public class League2Mapper implements RowMapper<League2>
{
	@Override
	public League2 map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		return new League2(rs.getString("name"),rs.getInt("id") ,rs.getInt("numTeams"));
	}
}
