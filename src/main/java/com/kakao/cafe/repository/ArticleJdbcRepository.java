package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.util.TimeStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Primary
public class ArticleJdbcRepository implements ArticleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(ArticleCreateCommand acc) {
        String sql= "INSERT INTO ARTICLES(WRITER, TITLE, CONTENT, CREATED_DATE) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                acc.getWriter(),
                acc.getTitle(),
                acc.getContents(),
                TimeStringParser.parseTimeToString(LocalDateTime.now()));
    }

    @Override
    public Article retrieve(Long id) {
        String sql = "SELECT * FROM ARTICLES WHERE ARTICLE_ID=?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
    }

    @Override
    public void modify(Long id, Article article) {
        String sql = "UPDATE ARTICLES SET WRITER=?, TITLE=?, CONTENT=? WHERE ARTICLE_ID=?";
        jdbcTemplate.update(sql,
                article.getWriter(),
                article.getTitle(),
                article.getContents(),
                id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ARTICLES WHERE ARTICLE_ID=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Article> toList() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    public RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("ARTICLE_ID"),
                rs.getString("WRITER"),
                rs.getString("TITLE"),
                rs.getString("CONTENT"),
                TimeStringParser.parseStringToTime(rs.getString("CREATED_DATE"))
        );
    }
}
