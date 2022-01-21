package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.repository.mapper.ArticleMapper;
import com.kakao.cafe.util.Paging;
import com.kakao.cafe.util.PagingRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> rowMapper;

    public ArticleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new ArticleMapper();
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = null;
        try {
            article = jdbcTemplate.queryForObject("SELECT * FROM ARTICLE WHERE id = ? AND is_deleted IS FALSE", rowMapper, id);
        } catch (EmptyResultDataAccessException ignored) {}
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM ARTICLE WHERE is_deleted IS FALSE", rowMapper);
    }

    @Override
    public Paging<Article> findByPageRequest(PagingRequest pagingRequest) {
        int total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ARTICLE WHERE is_deleted IS FALSE", Integer.class);
        List<Article> articles = jdbcTemplate.query("SELECT * FROM ARTICLE WHERE is_deleted IS FALSE ORDER BY CREATED_AT DESC LIMIT ? OFFSET ?", rowMapper, pagingRequest.getLimit(), pagingRequest.getOffset());
        return new Paging<>(articles, pagingRequest.getLimit(), pagingRequest.getOffset(), total);
    }

    @Override
    public Long save(Article article) {
        if(article.getId() == null) {
            return insertArticle(article);
        }
        return updateArticle(article);
    }

    @Override
    public Long delete(Article article) {
        jdbcTemplate.update("DELETE FROM ARTICLE WHERE id = ?", article.getId());
        return article.getId();
    }

    @Override
    public Long deleteAll() {
        int affectedRows = jdbcTemplate.update("DELETE FROM ARTICLE");
        return (long) affectedRows;
    }

    private Long insertArticle(Article article) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO ARTICLE(author, title, content, is_deleted, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, article.getAuthor());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getContent());
            statement.setBoolean(4, article.getDeleted());
            statement.setTimestamp(5, Timestamp.valueOf(article.getCreatedAt()));
            statement.setTimestamp(6, Timestamp.valueOf(article.getUpdatedAt()));
            return statement;
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    private Long updateArticle(Article article) {
        jdbcTemplate.update("UPDATE ARTICLE SET title = ?, content = ?, is_deleted = ?, updated_at = ? WHERE id = ?",
                article.getTitle(),
                article.getContent(),
                article.getDeleted(),
                article.getUpdatedAt(),
                article.getId());
        return article.getId();
    }
}
