package com.kakao.cafe.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-12 012
 * Time: 오후 2:18
 */
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO ARTICLES (WRITER, TITLE, CONTENTS, REPLY_COUNT, IS_DELETED, CREATED_DATE, MODIFIED_DATE)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getWriter(),
                article.getTitle(),
                article.getContents(),
                article.getReplyCount().toString(),
                article.getIsDeleted() ? "Y" : "N",
                article.getCreatedDate(),
                article.getModifiedDate());
        return article;
    }

    @Override
    public Article findArticleById(Integer id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID = ? AND IS_DELETED = 'N'";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES WHERE IS_DELETED = 'N'";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    @Override
    public Article update(Article article) {
        String sql = "UPDATE ARTICLES SET" +
                " TITLE=?, CONTENTS=?, IS_DELETED=?, MODIFIED_DATE=?" +
                " WHERE ID = ?";
        jdbcTemplate.update(sql,
                article.getTitle(),
                article.getContents(),
                article.getIsDeleted() ? "Y" : "N",
                article.getModifiedDate(),
                article.getId());
        return article;
    }
}
