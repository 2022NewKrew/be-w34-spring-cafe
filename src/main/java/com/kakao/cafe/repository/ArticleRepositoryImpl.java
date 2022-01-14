package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ArticleRepositoryImpl implements ArticleRepository{
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ArticleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("ARTICLE").usingGeneratedKeyColumns("ID");
    }

    @Override
    @Transactional(readOnly = false)
    public Article save(Article article) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        Number key = jdbcInsert.executeAndReturnKey(param);
        article.setId(key.intValue());
        this.jdbcTemplate.update(
                "INSERT INTO ARTICLE (TITLE, CONTENT, CREATEDTIME, VIEWS, WRITER) VALUES (?, ?, ?, ?, ?)",
                article.getTitle(), article.getContent(), article.getCreatedTime(),
                article.getViews(), article.getWriter().getId()
        );
        return article;
    }

    @Override
    public Optional<List<Article>> findAll() {
        String sql = "SELECT A.ARTICLE_ID AS ID, " +
                "A.TITLE AS TITLE, " +
                "A.CONTENT AS CONTENT, " +
                "A.CREATED_TIME AS CREATED_TIME," +
                "A.CONTENT AS CONTENT," +
                "U.USER_ID AS USER_ID," +
                "U.PASSWORD AS PASSWORD," +
                "U.NAME AS NAME, " +
                "U.EMAIL AS EMAIL, " +
                "U.CREATED_TIME AS USER_CREATED_TIME " +
                "FROM ARTICLE A JOIN WRITER U ON (U.USER_ID = A.WRITER)";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper());
        return Optional.of(result);
    }

    @Override
    public Article findById(String articleId) {
        String sql = "SELECT A.ARTICLE_ID AS ID, " +
                "A.TITLE AS TITLE, " +
                "A.CONTENT AS CONTENT, " +
                "A.CREATED_TIME AS CREATED_TIME," +
                "A.CONTENT AS CONTENT," +
                "U.USER_ID AS USER_ID," +
                "U.PASSWORD AS PASSWORD," +
                "U.NAME AS NAME, " +
                "U.EMAIL AS EMAIL, " +
                "U.CREATED_TIME AS USER_CREATED_TIME " +
                "FROM ARTICLE A JOIN WRITER U ON (U.USER_ID = A.WRITER)" +
                "WHERE U.USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper(), articleId);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(
                    rs.getInt("ARTICLE_ID"),
                    rs.getString("TITLE"),
                    rs.getString("CONTENT"),
                    rs.getDate("CREATED_TIME").toLocalDate(),
                    rs.getInt("CONTENT"),
                    new User(
                            rs.getInt("USER_ID"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getString("EMAIL"),
                            rs.getDate("USER_CREATED_TIME").toLocalDate()
                    )
            );
            return article;
        };
    }
}
