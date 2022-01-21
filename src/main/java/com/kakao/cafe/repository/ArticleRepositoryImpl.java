package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ArticleRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO ARTICLE (TITLE, CONTENT, CREATED_TIME, VIEWS, WRITER)" +
                    "VALUES (:title, :content, :createdTime, :views, :writer)";

        Map<String, Object> params = new HashMap<>();
        params.put("title", article.getTitle());
        params.put("content", article.getContent());
        params.put("createdTime", article.getCreatedTime());
        params.put("views", article.getViews());
        params.put("writer", article.getWriter().getId());

        this.namedParameterJdbcTemplate.update(sql, params);

        return article;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT A.ARTICLE_ID AS ARTICLE_ID, " +
                "A.TITLE AS TITLE, " +
                "A.CONTENT AS CONTENT, " +
                "A.CREATED_TIME AS CREATED_TIME," +
                "A.VIEWS AS VIEWS," +
                "U.USER_ID AS USER_ID," +
                "U.PASSWORD AS PASSWORD," +
                "U.NAME AS NAME, " +
                "U.EMAIL AS EMAIL, " +
                "U.CREATED_TIME AS USER_CREATED_TIME " +
                "FROM ARTICLE AS A "+
                "JOIN WRITER AS U "+
                "ON U.USER_ID = A.WRITER";
        return namedParameterJdbcTemplate.query(sql, articleRowMapper());
    }

    @Override
    public Article findById(String articleId) {
        String sql = "SELECT A.ARTICLE_ID AS ARTICLE_ID, " +
                "A.TITLE AS TITLE, " +
                "A.CONTENT AS CONTENT, " +
                "A.CREATED_TIME AS CREATED_TIME," +
                "A.VIEWS AS VIEWS," +
                "U.USER_ID AS USER_ID," +
                "U.PASSWORD AS PASSWORD," +
                "U.NAME AS NAME, " +
                "U.EMAIL AS EMAIL, " +
                "U.CREATED_TIME AS USER_CREATED_TIME " +
                "FROM ARTICLE AS A "+
                "JOIN WRITER AS U "+
                "ON U.USER_ID = A.WRITER " +
                "WHERE A.ARTICLE_ID = :articleId";
        Map<String, Object> param = new HashMap<>();
        param.put("articleId", articleId);
        return DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(sql, param, articleRowMapper()));
    }

    @Override
    public Article update(Article article) {
        String sql = "UPDATE ARTICLE " +
                "SET TITLE = :title, CONTENT = :content " +
                "WHERE ARTICLE_ID = :articleId";

        Map<String, Object> params = new HashMap<>();
        params.put("title", article.getTitle());
        params.put("content", article.getContent());
        params.put("articleId", article.getId());

        this.namedParameterJdbcTemplate.update(sql, params);

        return article;
    }

    @Override
    public void delete(String index) {
        String sql = "DELETE FROM ARTICLE " +
                "WHERE ARTICLE_ID = :articleId";

        Map<String, Object> params = new HashMap<>();
        params.put("articleId", index);

        this.namedParameterJdbcTemplate.update(sql, params);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getInt("ARTICLE_ID"),
                rs.getString("TITLE"),
                rs.getString("CONTENT"),
                rs.getDate("CREATED_TIME").toLocalDate(),
                rs.getInt("VIEWS"),
                new User(
                        rs.getInt("USER_ID"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getDate("USER_CREATED_TIME").toLocalDate()
                )
        );
    }
}
