package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.mapper.ArticleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_ARTICLE_QUERY = "INSERT INTO articles(writer, title, contents) VALUES(?,?,?)";
    private static final String SELECT_ARTICLES_QUERY = "SELECT id, writer, title, contents FROM articles";
    private static final String SELECT_ARTICLES_BY_ID_QUERY = "SELECT id, writer, title, contents FROM articles WHERE id = ?";

    @Override
    public Long insertArticle(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_ARTICLE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getWriter());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);
        article.updateId(keyHolder.getKey().longValue());
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SELECT_ARTICLES_QUERY, new ArticleRowMapper());
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_ARTICLES_BY_ID_QUERY, new ArticleRowMapper(), articleId));
    }
}
