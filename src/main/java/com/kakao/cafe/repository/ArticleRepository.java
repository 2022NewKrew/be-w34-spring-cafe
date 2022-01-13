package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticlePostDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ArticlePostDto article) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName("ARTICLE")
                .usingColumns("writer", "title", "contents")
                .usingGeneratedKeyColumns("id");

        param.put("writer", article.getWriter());
        param.put("title", article.getTitle());
        param.put("contents", article.getContents());

        int key = simpleJdbcInsert.executeAndReturnKey(param).intValue();

        if (key < 1)
            throw new SQLException("Article insertion fail.");
    }

    public List<Article> getAllArticles() {
        return jdbcTemplate.query(
                "select * from ARTICLE",
                new ArticleEntityMapper()
        );
    }

    public Article findById(int id) {

        return jdbcTemplate.queryForObject(
                "select * from ARTICLE where id = ?",
                new ArticleEntityMapper(),
                id
        );
    }

    public class ArticleEntityMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int count) throws SQLException {

            return new Article(
                    rs.getInt("id"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents")
            );
        };
    }
}
