package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class ArticleJdbcRepository implements ArticleRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article){
        String sql = "INSERT INTO ARTICLE VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                 article.getId(), article.getAuthor()
                ,article.getTitle(),article.getContents(),
                article.getUploadTime());
    }

    @Override
    public Long getNumberOfArticles(){
        String sql = "select count(*) from article";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<Article> findAll(){
        String sql = "select * from article";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("uploadTime")
        ));
    }

    @Override
    public Article findOneById(Long id){
        String sql = "select * from article where id=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("uploadTime")
        ),id);
    }
}
