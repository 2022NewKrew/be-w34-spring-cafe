package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.sql.ArticleQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleRepository implements CrudInterface<Article, Integer>{
    private JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article entity) {
        int update = jdbcTemplate.update(ArticleQuery.addArticleQuery(),entity.getWriter(), entity.getTitle(), entity.getContents());
        if(update > 0){
            return entity;
        }
        return null;
    }

    @Override
    public Article update(Article entity) {
        int update = jdbcTemplate.update(ArticleQuery.updateArticleQuery(), entity.getWriter(), entity.getTitle(), entity.getContents(), entity.getId());
        if(update > 0){
            return entity;
        }
        return null;
    }

    @Override
    public Article findById(Integer id) {
        Article article = jdbcTemplate.queryForObject(ArticleQuery.findArticleByIdQuery(), new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Article(rs.getInt("id"), rs.getString("writer"),rs.getString("title"),rs.getString("contents"));
            }
        }, id);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(ArticleQuery.findAllArticlesQuery(), new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Article(rs.getInt("id"), rs.getString("writer"),rs.getString("title"),rs.getString("contents"));
            }
        });
    }

}
