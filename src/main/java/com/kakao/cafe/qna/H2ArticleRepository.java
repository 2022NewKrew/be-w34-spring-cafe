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
public class H2ArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2ArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO ARTICLES (WRITER, TITLE, CONTENTS, REPLY_COUNT, CREATED_DATE, MODIFIED_DATE)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getWriter(),
                article.getTitle(),
                article.getContents(),
                article.getReplyCount().toString(),
                article.getCreatedDate(),
                article.getModifiedDate());
        return article;
    }

    @Override
    public Article findArticleById(Integer id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }
}
