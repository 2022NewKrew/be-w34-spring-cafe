package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    public static final String SELECT_ARTICLE_ALL = "SELECT * FROM TB_ARTICLE WHERE DELETED = FALSE";
    public static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM TB_ARTICLE WHERE ID = ? AND DELETED = FALSE";
    public static final String INSERT_ARTICLE = "INSERT INTO TB_ARTICLE(WRITER, TITLE, CONTENTS) VALUES (?, ?, ?)";
    public static final String UPDATE_ARTICLE = "UPDATE TB_ARTICLE SET WRITER = ?, TITLE = ?, CONTENTS = ? WHERE ID = ?";
    public static final String DELETE_ARTICLE_BY_ID_SOFT = "UPDATE TB_ARTICLE SET DELETED = TRUE WHERE ID = ?";
    public static final String SELECT_ARTICLE_BY_ID_WRITER = "SELECT * FROM TB_ARTICLE WHERE ID = ? AND WRITER = ?";

    private final JdbcTemplate jdbcTemplate;

    public int save(ArticleEntity articleEntity) {
        if (articleEntity.getId() == null) {
            return create(articleEntity);
        }
        return update(articleEntity);
    }

    public List<ArticleEntity> findAll() {
        return jdbcTemplate.query(SELECT_ARTICLE_ALL, this::convertArticleEntity);
    }

    public Optional<ArticleEntity> findById(Long id) {
        ArticleEntity articleEntity = jdbcTemplate.queryForObject(SELECT_ARTICLE_BY_ID, this::convertArticleEntity, id);
        return Optional.ofNullable(articleEntity);
    }

    private int create(ArticleEntity articleEntity) {
        return jdbcTemplate.update(INSERT_ARTICLE, articleEntity.getWriter(), articleEntity.getTitle(), articleEntity.getContents());
    }

    private int update(ArticleEntity articleEntity) {
        return jdbcTemplate.update(UPDATE_ARTICLE, articleEntity.getWriter(), articleEntity.getTitle(), articleEntity.getContents(), articleEntity.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_ARTICLE_BY_ID_SOFT, id);
    }

    public List<ArticleEntity> findByIdAndWriter(Long id, String username) {
        return jdbcTemplate.query(SELECT_ARTICLE_BY_ID_WRITER, this::convertArticleEntity, id, username);
    }

    private ArticleEntity convertArticleEntity(ResultSet rs, int rowNum) throws SQLException {
        return ArticleEntity.builder()
                .id(rs.getLong("id"))
                .writer(rs.getString("writer"))
                .title(rs.getString("title"))
                .contents(rs.getString("contents"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
