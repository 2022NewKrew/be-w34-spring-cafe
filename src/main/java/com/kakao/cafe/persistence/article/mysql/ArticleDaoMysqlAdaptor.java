package com.kakao.cafe.persistence.article.mysql;

import com.kakao.cafe.domain.article.*;
import com.kakao.cafe.persistence.H2QueryBuilder;
import com.kakao.cafe.persistence.article.mysql.dto.ArticleEntityDtoMapper;
import com.kakao.cafe.persistence.article.mysql.dto.ArticleTableDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleDaoMysqlAdaptor implements FindArticlePort, WriteArticlePort, UpdateArticlePort, DeleteArticlePort {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ArticleTableDtoRowMapper articleTableDtoRowMapper;
    private final ArticleEntityDtoMapper articleEntityDtoMapper;
    private final H2QueryBuilder h2QueryBuilder;

    public ArticleDaoMysqlAdaptor(NamedParameterJdbcTemplate jdbcTemplate, ArticleTableDtoRowMapper articleRowMapper, ArticleEntityDtoMapper articleEntityDtoMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleTableDtoRowMapper = articleRowMapper;
        this.articleEntityDtoMapper = articleEntityDtoMapper;
        this.h2QueryBuilder = new H2QueryBuilder("article", List.of("article_id", "user_id", "article_created_at", "article_title", "article_contents"));
    }

    @Override
    public List<Article> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        List<Article> articles = jdbcTemplate.query(h2QueryBuilder.selectAll(), parameters, articleTableDtoRowMapper)
                .stream()
                .map(articleEntityDtoMapper::convertDtoToEntity)
                .collect(Collectors.toList());
        return articles;
    }

    @Override
    public Optional<Article> findById(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("article_id", index);

        ArticleTableDto articleTableDto = jdbcTemplate.queryForObject(h2QueryBuilder.selectOneByField("article_id"), parameters, articleTableDtoRowMapper);
        try {
            return Optional.of(articleEntityDtoMapper.convertDtoToEntity(articleTableDto));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(ArticleVo article) {
        Map<String, Object> parameters = Map.of("user_id", article.getWriter().getUserId(),
                "article_created_at", LocalDateTime.now(),
                "article_title", article.getTitle(),
                "article_contents", article.getContents());

        jdbcTemplate.update(h2QueryBuilder.insertOne(List.of("user_id", "article_created_at", "article_title", "article_contents")), parameters);
    }

    @Override
    public void update(ArticleVo article) {
        Map<String, Object> parameters = Map.of(
                "article_id", article.getId(),
                "article_title", article.getTitle(),
                "article_contents", article.getContents()
        );

        jdbcTemplate.update(h2QueryBuilder.updateOne(List.of("article_title", "article_contents"), List.of("article_id")), parameters);
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> parameters = Map.of(
                "article_id", id);

        jdbcTemplate.update(h2QueryBuilder.deleteOne(List.of("article_id")), parameters);
    }
}
