package ro.utcn.spet.example.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.example.a1.entity.StackUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StackUserMapper implements RowMapper<StackUser> {

	@Override
	public StackUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new StackUser(rs.getInt("id"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getString("email_address"),
	        	rs.getString("password"));
	}

}
