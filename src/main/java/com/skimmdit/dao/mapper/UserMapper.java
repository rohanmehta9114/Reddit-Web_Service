package com.skimmdit.dao.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.skimmdit.representation.*;

public class UserMapper implements ResultSetMapper<UserRepresentation> {

	@Override
	public UserRepresentation map(int arg0, ResultSet arg1,
			StatementContext arg2) throws SQLException {
		// TODO Auto-generated method stub
		return new UserRepresentation(arg1.getInt("userid"),arg1.getString("username"),arg1.getString("email"));
	}	

}
