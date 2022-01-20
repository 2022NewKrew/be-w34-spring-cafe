package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateArticleRepository implements ArticleRepository {

    DateTimeFormatter articleTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveArticle(Article article) {
        jdbcTemplate.update(
                "insert into articles(title, author, content) values(?,?,?)",
                article.getTitle(), article.getAuthor(), article.getContent());
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
    public void editArticle(Long articleId, Article article) {
        jdbcTemplate.update(
                "update articles set title= ?, content = ? where id = ?",
                article.getTitle(), article.getContent(), articleId);
    }

    @Override
    public void deleteArticle(Long articleId) {
        jdbcTemplate.update(
                "delete from articles where id = ?", articleId);
    }


    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(
                    rs.getString("title"),
                    rs.getString("content"));
            article.setId(rs.getLong("id"));
            article.setAuthor(rs.getString("author"));
            article.setCreated(rs.getTimestamp("created").toLocalDateTime().format(articleTimeFormatter));
            article.setViews(rs.getInt("views"));
            return article;
        };
    }
}
