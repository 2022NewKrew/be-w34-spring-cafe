package com.kakao.cafe.persistence.article.h2;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.FindArticlePort;
import com.kakao.cafe.domain.article.WriteArticlePort;
import com.kakao.cafe.persistence.H2QueryBuilder;
import com.kakao.cafe.persistence.article.ArticleRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticleDaoH2Adaptor implements FindArticlePort, WriteArticlePort {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;
    private final H2QueryBuilder h2QueryBuilder;

    public ArticleDaoH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, ArticleRowMapper articleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleRowMapper = articleRowMapper;
        this.h2QueryBuilder = new H2QueryBuilder("article", List.of("article_id", "article_writer", "article_created_at", "article_title", "article_contents"));
    }

    @Override
    public List<Article> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        List<Article> articles = jdbcTemplate.query(h2QueryBuilder.selectAll(), parameters, articleRowMapper);
        return articles;
    }

    @Override
    public Optional<Article> findById(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("article_id", index);

        try {
            return Optional.of(jdbcTemplate.queryForObject(h2QueryBuilder.selectOneByField("article_id"), parameters, articleRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(ArticleVo article) {
        Map<String, Object> parameters = Map.of("article_writer", article.getWriter(),
                "article_created_at", LocalDateTime.now(),
                "article_title", article.getTitle(),
                "article_contents", article.getContents());

        jdbcTemplate.update(h2QueryBuilder.insertOne(List.of("article_writer", "article_created_at", "article_title", "article_contents")), parameters);
    }
}
