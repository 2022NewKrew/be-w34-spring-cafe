package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleRowMapper = getArticleRowMapper();

    public ArticleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO articles (user_id, title, content, view_num, created_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getUser().getId(), article.getTitle(), article.getContent(),
            article.getViewNum(), article.getCreatedDate());
        return article;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles a JOIN users u ON a.user_id = u.id";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM articles a JOIN users u ON a.user_id = u.id WHERE a.id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, id).stream().findFirst();
    }

    @Override
    public void updateViewNum(Long id) {
        String sql = "UPDATE articles SET view_num = view_num+1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Article updateArticle) {
        String sql = "UPDATE articles SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, updateArticle.getTitle(), updateArticle.getContent(), updateArticle.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM articles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public RowMapper<Article> getArticleRowMapper() {
        return (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("content"),
            getUser(rs),
            rs.getLong("view_num"),
            rs.getTimestamp("created_date").toLocalDateTime());
    }

    private User getUser(ResultSet rs) throws SQLException {
        return new User(rs.getLong("user_id"),
            rs.getString("email"),
            rs.getString("nickname"),
            rs.getString("nickname"),
            rs.getTimestamp("created_date").toLocalDateTime());
    }
}
