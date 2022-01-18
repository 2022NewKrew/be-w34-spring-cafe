package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DbArticleRepository implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", article.getTitle());
        parameters.put("contents", article.getContents());
        parameters.put("writer", article.getWriter());
        parameters.put("active", article.isActive());
        parameters.put("createdTime", article.getCreatedTime());
        parameters.put("updatedTime", article.getUpdatedTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article;
    }

    @Override
    public Optional<Article> findOneById(Long id) {
        return jdbcTemplate.query("select * from article where id = ?", articleRowMapper(), id)
                .stream()
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from article");
    }

    @Override
    public void update(long id, String title, String contents, String writer) {
        jdbcTemplate.update("update article set title = ?, contents = ?, writer = ? where id = ?", title, contents, writer, id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from article where id = ?", id);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setContents(rs.getString("contents"));
            article.setWriter(rs.getString("writer"));
            article.setActive(rs.getBoolean("active"));
            article.setCreatedTime(rs.getTimestamp("createdTime"));
            article.setUpdatedTime(rs.getTimestamp("updatedTime"));
            return article;
        };
    }
}
