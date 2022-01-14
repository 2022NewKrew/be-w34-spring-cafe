package com.kakao.cafe.domain.Repository.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.mapper.article.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    public void save(Article article) {
        this.jdbcTemplate.update("INSERT INTO ARTICLES (writer, title, contents) VALUES (?, ?, ?)",
                article.getWriter(), article.getTitle(), article.getContents());
    }

    public List<Article> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM ARTICLES", this.articleMapper);
    }

    public Article findById(int id) {
        Article article = this.jdbcTemplate.queryForObject("SELECT * FROM ARTICLES WHERE id = ?", this.articleMapper, id);
        return article;
    }


}
