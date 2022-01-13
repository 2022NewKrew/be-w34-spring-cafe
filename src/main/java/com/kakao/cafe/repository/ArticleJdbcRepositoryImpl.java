package com.kakao.cafe.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.mapper.ArticleRowMapper;

import lombok.RequiredArgsConstructor;

@Primary
@Repository
@RequiredArgsConstructor
public class ArticleJdbcRepositoryImpl implements ArticleRepository {
	private final JdbcTemplate jdbcTemplate;
	private final ArticleRowMapper articleRowMapper;

	@Override
	public void save(Article article) {
		String sql = "INSERT INTO article(writer, title, contents) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, article.getWriter(), article.getTitle(), article.getContents());
	}

	@Override
	public List<Article> findAll() {
		String sql = "SELECT * FROM article";
		List<Article> articleList = jdbcTemplate.query(sql, articleRowMapper);

		return articleList;
	}

	@Override
	public Article findById(int id) {
		String sql = "SELECT * FROM article WHERE id = ?";
		Article article = jdbcTemplate.queryForObject(sql, articleRowMapper, id);

		return article;
	}
}
