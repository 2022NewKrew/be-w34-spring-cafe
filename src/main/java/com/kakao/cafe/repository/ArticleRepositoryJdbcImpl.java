package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryJdbcImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Article> articleMapper = (rs, rowNum) -> {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setUserId(rs.getLong("user_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
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
        String INSERT_ARTICLE =
                "INSERT INTO article (user_id, title, content) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(INSERT_ARTICLE, article.getUserId(), article.getTitle(), article.getContent());
    }

    private void update(Article article) {
        String UPDATE_ARTICLE =
                "UPDATE article " +
                "SET title=?, content=? " +
                "WHERE id=?";
        jdbcTemplate.update(UPDATE_ARTICLE, article.getTitle(), article.getContent(), article.getId());
    }

    @Override
    public List<Article> findAll() {
        String SELECT_ARTICLES =
                "SELECT id, user_id, title, content, creation_time " +
                "FROM article " +
                "WHERE deleted=false";
        return jdbcTemplate.query(SELECT_ARTICLES, articleMapper);
    }

    @Override
    public Optional<Article> findById(Long id) {
        String SELECT_ARTICLE =
                "SELECT id, user_id, title, content, creation_time " +
                "FROM article " +
                "WHERE id=? AND deleted=false";
        return jdbcTemplate.query(SELECT_ARTICLE, articleMapper, id)
                .stream().findFirst();
    }

    @Override
    public void deleteById(Long id) {
        String DELETE_ARTICLE = "UPDATE article SET deleted=true WHERE id=?";
        jdbcTemplate.update(DELETE_ARTICLE, id);
    }
}
