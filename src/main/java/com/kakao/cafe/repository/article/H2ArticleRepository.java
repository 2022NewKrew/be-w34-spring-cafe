package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class H2ArticleRepository implements ArticleRepository {

	private final JdbcTemplate jdbcTemplate;
	private int maxId;

	@Override
	public void save(Article article) {
		jdbcTemplate.update(
				"INSERT INTO ARTICLE VALUES (?, ?, ?, ?, ?, ?)",
				maxId++, article.getWriter(), article.getTitle(), article.getContents(), article.getCreatedDate(), article.getCreatedTime());
	}

	@Override
	public List<Article> findAll() {
		return jdbcTemplate.query(
				"SELECT * FROM ARTICLE", (rs, rowNum) -> {
					Article article = new Article();
					article.setIndex(rs.getInt("ID"));
					article.setWriter(rs.getString("WRITER"));
					article.setTitle(rs.getString("TITLE"));
					article.setContents(rs.getString("CONTENTS"));
					article.setCreatedDate(rs.getString("CREATEDDATE"));
					article.setCreatedTime(rs.getString("CREATEDTIME"));
					return article;
				});
	}

	@Override
	public Article findByIndex(int index) {
		return jdbcTemplate.queryForObject("SELECT * FROM ARTICLE WHERE id = ?", (rs, rowNum) -> {
			Article article = new Article();
			article.setIndex(rs.getInt("ID"));
			article.setWriter(rs.getString("WRITER"));
			article.setTitle(rs.getString("TITLE"));
			article.setContents(rs.getString("CONTENTS"));
			article.setCreatedDate(rs.getString("CREATEDDATE"));
			article.setCreatedTime(rs.getString("CREATEDTIME"));
			return article;
		}, index);
	}
}
