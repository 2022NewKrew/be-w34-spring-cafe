package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository("ArticleRepositoryJdbcImpl")
public class ArticleRepositoryJdbcImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) ->
                Article.builder()
                        .articleId(rs.getLong("ARTICLE_ID"))
                        .title(rs.getString("TITLE"))
                        .writerId(rs.getString("WRITER_ID"))
                        .content(rs.getString("CONTENT"))
                        .createdDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime())
                        .formattedCreatedDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isDeleted(rs.getBoolean("IS_DELETED")).build();
    }

    @Override
    public boolean saveArticle(Article article) {
        jdbcTemplate.update("INSERT INTO ARTICLES (TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED) VALUES ( ?, ?, ?, ?, ?)",
                article.getTitle(), article.getWriterId(), article.getContent(), LocalDateTime.now(), article.isDeleted());
        return true;
    }

    @Override
    public List<Article> findAllArticle() {
        return jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM ARTICLES WHERE IS_DELETED = FALSE",
                articleRowMapper());
    }

    @Override
    public Optional<Article> findArticleByArticleId(long articleId) {
        List<Article> result = jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM ARTICLES WHERE ARTICLE_ID = ? AND IS_DELETED = FALSE",
                articleRowMapper(), articleId);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findArticlesByWriterId(String writerId) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM ARTICLES WHERE WRITER_ID = ? AND IS_DELETED = FALSE",
                articleRowMapper(), writerId);
    }

    @Override
    public List<Article> findArticlesByStartAndWantedCountPerPage(long start, long countPerPage) {
        return jdbcTemplate.query("SELECT ARTICLE_ID, TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED FROM ARTICLES WHERE IS_DELETED = FALSE ORDER BY ARTICLE_ID DESC LIMIT ?, ?",
                articleRowMapper(), start, countPerPage);
    }

    @Override
    public long countAllAvailableArticles() {
        return jdbcTemplate.query("SELECT COUNT(*) FROM ARTICLES WHERE IS_DELETED = FALSE",
                (rs, rowNum) -> rs.getLong(1)).get(0);
    }

    @Override
    public boolean modifyArticle(Article article) {
        jdbcTemplate.update("UPDATE ARTICLES SET TITLE = ?, CONTENT = ? WHERE ARTICLE_ID = ?",
                article.getTitle(), article.getContent(), article.getArticleId());
        return true;
    }

    @Override
    public boolean deleteArticle(long articleId) {
        jdbcTemplate.update("UPDATE ARTICLES SET IS_DELETED = TRUE WHERE ARTICLE_ID = ?", articleId);
        return true;
    }
}
