package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ArticleJdbcRepository implements ArticleRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addArticle(Article article) {
        String sql = "INSERT INTO articles(name,title,contents,createdAt) VALUES (?,?,?,CURRENT_TIMESTAMP())";
        jdbcTemplate.update(sql,
                article.getName(),
                article.getTitle(),
                article.getContents()
        );
    }

    @Override
    public List<Article> getArticles() {
        String sql = "SELECT * FROM articles";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public Article getArticleByCondition(String key, String value) {
        String sql = String.format("SELECT * FROM articles WHERE %s = %s", key, value);
        return (Article) jdbcTemplate.query(sql, new ArticleRowMapper()).stream().findAny().orElse(null);
    }
}
