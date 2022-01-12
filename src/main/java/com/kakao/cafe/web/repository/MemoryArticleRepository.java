package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository{
    private final Logger logger;

    private final JdbcTemplate jdbcTemplate;

    Map<Integer, Article> articleMap;

    public MemoryArticleRepository(JdbcTemplate jdbcTemplate) {
        this.articleMap = new HashMap<>();
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        String sql = "INSERT INTO ARTICLE (WRITER, TITLE, CONTENTS) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                articleDTO.getWriter(),
                articleDTO.getTitle(),
                articleDTO.getContents()
        );
        return new Article(1, "writer", "title", "contents");
    }

    @Override
    public List<Article> getArticleList() {
        String sql = "SELECT * FROM ARTICLE";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Article.class)
        );
    }

    @Override
    public Article getArticleById(int id) {
        Optional<Article> foundArticle = articleMap.values().stream()
                .filter(article -> article.getId() == id)
                .findFirst();
        return foundArticle.orElse(null);
    }
}
