package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryJdbcImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Article> articleMapper = (rs, rowNum) -> {
        Article article = new Article(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("content")
        );
        article.setId(rs.getLong("id"));
        article.setCreationTime(rs.getTimestamp("creation_time"));
        return article;
    };

    @Autowired
    public ArticleRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        if (article.getId() == null) {
            insert(article);
        } else {
            update(article);
        }
    }

    private void insert(Article article) {
        String INSERT_ARTICLE = "INSERT INTO ARTICLE (WRITER, TITLE, CONTENT) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(INSERT_ARTICLE, article.getWriter(), article.getTitle(), article.getContent());
    }

    private void update(Article article) {
        String UPDATE_ARTICLE = "UPDATE ARTICLE " +
                "SET TITLE=?, CONTENT=? " +
                "WHERE ID=?";
        jdbcTemplate.update(UPDATE_ARTICLE, article.getTitle(), article.getContent(), article.getId());
    }

    @Override
    public List<Article> findAll() {
        String SELECT_ARTICLES = "SELECT ID, WRITER, TITLE, CONTENT, CREATION_TIME FROM ARTICLE";
        return jdbcTemplate.query(SELECT_ARTICLES, articleMapper);
    }

    @Override
    public Article findById(Long id) throws NotFoundException {
        String SELECT_ARTICLE = "SELECT ID, WRITER, TITLE, CONTENT, CREATION_TIME " +
                "FROM ARTICLE " +
                "WHERE ID=?";
        try {
            return jdbcTemplate.queryForObject(SELECT_ARTICLE, articleMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("해당 아이디의 게시물이 없습니다.");
        }
    }

    @Override
    public void deleteById(Long id) {
        String DELETE_ARTICLE = "DELETE FROM ARTICLE WHERE id=?";
        jdbcTemplate.update(DELETE_ARTICLE, id);
    }
}
