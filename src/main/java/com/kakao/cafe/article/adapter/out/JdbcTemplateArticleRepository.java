package com.kakao.cafe.article.adapter.out;

import com.kakao.cafe.article.application.port.out.CreateArticleDto;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleId;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateArticleRepository implements SaveArticlePort, LoadArticlePort {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleMapper;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.articleMapper = articleRowMapper();
    }

    @Override
    public void save(CreateArticleDto createArticleDto) {
        String title = createArticleDto.getTitle();
        String content = createArticleDto.getContent();

        jdbcTemplate.update(
            "INSERT INTO `ARTICLE` (title, content) VALUES (?, ?)",
            title, content);
    }

    @Override
    public Optional<Article> load(ArticleId articleId) {
        Article article = jdbcTemplate.queryForObject(
            "SELECT id, title, content, created_at FROM `ARTICLE` WHERE id=?",
            articleMapper,
            articleId.getValue());
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> loadAll() {
        return jdbcTemplate.query("SELECT id, title, content, created_at FROM `ARTICLE`",
            articleMapper);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            ArticleId articleId = new ArticleId(rs.getInt("id"));
            String title = rs.getString("title");
            String content = rs.getString("content");
            Instant created_at = rs.getTimestamp("created_at").toInstant();

            return new Article(articleId, title, content, created_at);
        };
    }
}
