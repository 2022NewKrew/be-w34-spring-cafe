package com.kakao.cafe.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kakao.cafe.entity.Article;

@Component
public class ArticleRowMapper implements RowMapper<Article> {
	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Article.builder()
			.id(rs.getInt("id"))
			.writer(rs.getString("writer"))
			.title(rs.getString("title"))
			.contents(rs.getString("contents"))
			.createTime(rs.getTimestamp("create_time").toLocalDateTime())
			.build();
	}
}
