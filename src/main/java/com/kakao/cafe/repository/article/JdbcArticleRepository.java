package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.mapper.ArticleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;

    private static final String INSERT_ARTICLE_QUERY = "INSERT INTO articles(user_id, title, contents) VALUES(?,?,?)";
    private static final String UPDATE_ARTICLE_QUERY = "UPDATE articles SET title=?, contents=? WHERE id = ?";
    private static final String SELECT_ARTICLES_QUERY
            = "SELECT a.id as id, a.user_id as writer_id, u.user_name as writer_name, a.title as title, a.contents as contents FROM articles as a INNER JOIN users as u ON a.user_id = u.user_id WHERE a.is_deleted = false";
    private static final String SELECT_ARTICLES_BY_ID_QUERY
            = "SELECT a.id as id, a.user_id as writer_id, u.user_name as writer_name, a.title as title, a.contents as contents FROM articles as a INNER JOIN users as u ON a.user_id = u.user_id WHERE a.id = ? AND a.is_deleted = false";
    private static final String DELETE_ARTICLE_QUERY = "UPDATE articles SET is_deleted=true WHERE id = ?";

    @Override
    public Long insert(Article article) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(INSERT_ARTICLE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, article.getWriter().getUserId());
                ps.setString(2, article.getTitle());
                ps.setString(3, article.getContents());
                return ps;
            }, keyHolder);
            article.updateId(keyHolder.getKey().longValue());
        } catch (DataAccessException ex) {
            log.warn(ex.getMessage());
            throw ex;
        }
        return article.getId();
    }

    @Override
    public Long update(Article article) {
        jdbcTemplate.update(UPDATE_ARTICLE_QUERY, article.getTitle(), article.getContents(), article.getId());
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SELECT_ARTICLES_QUERY, articleRowMapper);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        List<Article> articles = jdbcTemplate.query(SELECT_ARTICLES_BY_ID_QUERY, articleRowMapper, articleId);
        return articles.stream().findFirst();
    }

    @Override
    public void delete(Long articleId) {
        try {
            jdbcTemplate.update(DELETE_ARTICLE_QUERY, articleId);
        } catch (DataAccessException ex) {
            log.warn(ex.getMessage());
            throw ex;
        }
    }
}
