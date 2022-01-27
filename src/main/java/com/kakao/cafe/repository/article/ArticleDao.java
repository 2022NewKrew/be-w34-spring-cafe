package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Article> articleMapper = (rs, rowNum) -> Article.builder()
            .id(rs.getLong("id"))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .userId(rs.getString("userId"))
            .date(rs.getString("date")).build();

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Article article) {
        String sql = "INSERT INTO ARTICLES (title,content,userId,date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getUserId(), article.getDate());
    }

    public Article selectById(long id) {
        String sql = "SELECT id,title,content,userId,date FROM ARTICLES WHERE id=?";
        return jdbcTemplate.queryForObject(sql, articleMapper, id);
    }

    public List<Article> selectAll() {
        String sql = "SELECT id,title,content,userId,date FROM ARTICLES";
        return jdbcTemplate.query(sql, articleMapper);
    }
}
