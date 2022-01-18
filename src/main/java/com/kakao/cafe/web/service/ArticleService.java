package com.kakao.cafe.web.service;

import com.kakao.cafe.ArticleMapper;
import com.kakao.cafe.UserMapper;
import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {
    private final JdbcTemplate jdbcTemplate;

    private ArticleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getArticles() {
        List<Article> articles = jdbcTemplate.query(QueryConstants.articleSelect, new ArticleMapper());
        for (Article a : articles) {
            a.setAuthor(jdbcTemplate.queryForObject(QueryConstants.userSelectById, new UserMapper(), a.getAuthorId()));
        }
        return articles;
    }

    public void addArticle(Article article) {
        jdbcTemplate.update(QueryConstants.articleInsert, article.getAuthorId(), article.getTitle(), article.getContent());
    }

    public Article getByArticleId(int id) {
        Article article = jdbcTemplate.queryForObject(QueryConstants.articleSelectById, new ArticleMapper(), id);
        article.setAuthor(jdbcTemplate.queryForObject(QueryConstants.userSelectById, new UserMapper(), article.getAuthorId()));
        return article;
    }

    public void updateArticle(int id, Article updateArticle) {
        if (updateArticle.getTitle().isBlank())
            throw new IllegalArgumentException("제목이 빈 값일 수 없습니다.");
        if (updateArticle.getContent().isBlank())
            throw new IllegalArgumentException("내용이 빈 값일 수 없습니다.");
        jdbcTemplate.update(QueryConstants.articleUpdate, updateArticle.getTitle(), updateArticle.getContent(), id);
    }

    public void deleteArticle(int id) {
        jdbcTemplate.update(QueryConstants.articleDelete, id);
    }
}
