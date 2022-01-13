package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveArticle(Article article) {
        jdbcTemplate.update(
                "insert into articles(title, author, content) values(?,?,?)",
                article.getTitle(), "익명", article.getContent());
    }

    @Override
    public Optional<Article> findByArticleId(Long id) {
        List<Article> result = jdbcTemplate.query("select * from articles where id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> result = jdbcTemplate.query("select * from articles where title = ?", articleRowMapper(), title);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query("select * from articles", articleRowMapper());
    }

    @Override
    public void saveComment(Comment comment) {
        jdbcTemplate.update(
                "insert into comments(author, content, articleId) values(?,?,?)",
                "익명", comment.getContent(), comment.getArticleId());
    }

    @Override
    public List<Comment> findCommentsOf(Long articleId) {
        return jdbcTemplate.query("select * from comments where articleId = ?", comment(), articleId);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article =  new Article(
                    rs.getString("title"),
                    rs.getString("content"));
            article.setId(rs.getLong("id"));
            article.setAuthor(rs.getString("author"));
            article.setCreated(rs.getTimestamp("created"));
            article.setViews(rs.getInt("views"));
            return article;
        };
    }

    private RowMapper<Comment> comment() {
        return (rs, rowNum) -> {
            Comment comment = new Comment(
                    rs.getString("content"),
                    rs.getLong("articleId"));
            comment.setId(rs.getLong("id"));
            comment.setAuthor(rs.getString("author"));
            comment.setCreated(rs.getTimestamp("created"));
            return comment;
        };
    }
}
