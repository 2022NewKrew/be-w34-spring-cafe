package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleSaveDto;
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
    public void save(ArticleSaveDto articleSaveDTO) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleJdbcInsert.withTableName("ARTICLE_TABLE").usingGeneratedKeyColumns("ID", "CREATED_AT");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", articleSaveDTO.getTitle());
        parameters.put("content", articleSaveDTO.getContent());
        parameters.put("userId", articleSaveDTO.getUserId());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public Article findArticleById(int id) {
        List<Article> articles = jdbcTemplate.query("SELECT ID, TITLE, CONTENT, CREATED_AT, AT.USERID, NAME FROM ARTICLE_TABLE AT JOIN USER_TABLE UT on UT.USERID = AT.USERID AND ID = ?", articleRowMapper(), id);
        return articles.stream().findAny().orElse(null);
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query("SELECT ID, TITLE, CONTENT, CREATED_AT, AT.USERID, NAME FROM ARTICLE_TABLE AT JOIN USER_TABLE UT on UT.USERID = AT.USERID", articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) ->
                new Article(rs.getInt("ID"),
                        rs.getString("TITLE"),
                        rs.getString("CONTENT"),
                        rs.getString("USERID"),
                       rs.getString("NAME"),
                       rs.getTimestamp("CREATED_AT"));
    }
}
