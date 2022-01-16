package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleSaveDTO;
import com.kakao.cafe.domain.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDBRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ArticleDBRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(ArticleSaveDTO articleSaveDTO) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleJdbcInsert.withTableName("ARTICLE_TABLE").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", articleSaveDTO.getTitle());
        parameters.put("content", articleSaveDTO.getContent());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public Article findArticleById(int id) {
        List<Article> articles = jdbcTemplate.query("SELECT ID, TITLE, CONTENT FROM ARTICLE_TABLE WHERE ID = ?", articleRowMapper(), id);
        return articles.stream().findAny().orElse(null);
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query("SELECT ID, TITLE, CONTENT FROM ARTICLE_TABLE", articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) ->
                new Article(rs.getInt("ID"),
                        rs.getString("TITLE"),
                        rs.getString("CONTENT"));
    }
}
