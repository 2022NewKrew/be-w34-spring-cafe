package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Article article){
        String sql = "INSERT INTO ARTICLES VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,article.getTitle(),article.getContent(),article.getCreateUserId(),article.getCreateDate(),article.getViews());
    }

    public void findById(int id){
        String sql = "SELECT * FROM ARTICLES WHERE ID=?";
        // jdbcTemplate.queryForObject(sql,String.valueOf(id));
    }

    public ArticleList getArticleList() {
        return null;
    }
}
