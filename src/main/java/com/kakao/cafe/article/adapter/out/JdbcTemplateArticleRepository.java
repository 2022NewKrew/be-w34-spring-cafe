package com.kakao.cafe.article.adapter.out;

import com.kakao.cafe.article.application.port.out.CreateArticleDto;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleId;
import java.sql.Types;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateArticleRepository implements SaveArticlePort, LoadArticlePort {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleMapper;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.articleMapper = articleRowMapper();
    }

    @Override
    public ArticleId save(CreateArticleDto createArticleDto) {
        final String CREATE_ARTICLE_SQL = "INSERT INTO `ARTICLE` (title, content) VALUES (?, ?)";

        PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory(
            CREATE_ARTICLE_SQL, Types.VARCHAR, Types.VARCHAR);
        creatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = creatorFactory.newPreparedStatementCreator(
            Arrays.asList(createArticleDto.getTitle(), createArticleDto.getContent()));
        return new ArticleId(jdbcTemplate.update(psc));
    }

    @Override
    public Optional<Article> load(ArticleId articleId) {
        final String LOAD_ARTICLE_SQL = "SELECT id, title, content, created_at FROM `ARTICLE` WHERE id=?";

        Article article = jdbcTemplate.queryForObject(LOAD_ARTICLE_SQL, articleMapper,
            articleId.getValue());
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> loadAll() {
        final String LOAD_ARTICLES_SQL = "SELECT id, title, content, created_at FROM `ARTICLE`";
        return jdbcTemplate.query(LOAD_ARTICLES_SQL, articleMapper);
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
