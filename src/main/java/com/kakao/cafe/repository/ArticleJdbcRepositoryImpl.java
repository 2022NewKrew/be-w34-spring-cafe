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
	private final static String SAVE_QUERY = "INSERT INTO article(writer, title, contents) VALUES (?, ?, ?)";
	private final static String FIND_ALL_QUERY = "SELECT * FROM article";
	private final static String FIND_BY_ID_QUERY = "SELECT * FROM article WHERE id = ?";

	private final JdbcTemplate jdbcTemplate;
	private final ArticleRowMapper articleRowMapper;

	@Override
	public void save(Article article) {
		jdbcTemplate.update(SAVE_QUERY, article.getWriter(), article.getTitle(), article.getContents());
	}

	@Override
	public List<Article> findAll() {
		List<Article> articleList = jdbcTemplate.query(FIND_ALL_QUERY, articleRowMapper);

		return articleList;
	}

	@Override
	public Article findById(int id) {
		Article article = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, articleRowMapper, id);

		return article;
	}
}
