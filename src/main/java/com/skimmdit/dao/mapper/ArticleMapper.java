package com.skimmdit.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.skimmdit.representation.ArticleRepresentation;


public class ArticleMapper implements ResultSetMapper<ArticleRepresentation> {

	@Override
	public ArticleRepresentation map(int arg0, ResultSet arg1, StatementContext arg2)
			throws SQLException {
		// TODO Auto-generated method stub
		return new ArticleRepresentation(arg1.getInt("articleid"),arg1.getString("articlename"),arg1.getString("link"),arg1.getInt("votediff"));
	}

}
