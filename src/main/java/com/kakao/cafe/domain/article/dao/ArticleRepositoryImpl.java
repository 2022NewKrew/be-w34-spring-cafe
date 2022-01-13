package com.kakao.cafe.domain.article.dao;

import com.kakao.cafe.domain.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        final String sql = "INSERT INTO `ARTICLE` (`author`, `title`, `content`, `created_at`) VALUES(?, ?, ?, ?)";

        Object[] parameters = {
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                LocalDate.now()
        };

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Optional<Article> findById(Long id) {
        final String sql = "SELECT `id`, `author`, `title`, `content`, `created_at` from `ARTICLE` where `id` = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findByUserId(String userId) {
        final String sql = "SELECT `id`, `author`, `title`, `content`, `created_at` from `ARTICLE` where `author` = ?";
        return jdbcTemplate.query(sql, articleRowMapper(), userId);
    }

    @Override
    public List<Article> findAll() {
        final String sql = "SELECT `id`, `author`, `title`, `content`, `created_at` from `ARTICLE`";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (resultSet, rowNum) -> new Article(
                resultSet.getLong("id"),
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getDate("created_at").toLocalDate()
        );
    }
}
