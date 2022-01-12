package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.NoSuchArticle;
import com.kakao.cafe.repository.dto.ArticleResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private static AtomicLong id = new AtomicLong(0L);
    private final JdbcTemplate jdbcTemplate;

    public ArticleJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table article (id BIGINT NOT NULL, writer varchar(15), title varchar(50), content TEXT, createdAt DATE, numOfComment number)");
    }

    @Override
    public Long save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article");

        long articleId = id.incrementAndGet();
        article.setArticleId(articleId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", articleId);
        parameters.put("content", article.getContent());
        parameters.put("title", article.getTitle());
        parameters.put("writer", article.getWriter());
        parameters.put("createdAt", article.getCreatedAt());
        parameters.put("numOfComment", article.getNumOfComment());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
        return articleId;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Article findById(Long postId) {
        List<Article> query = jdbcTemplate.query("select * from article where id=?", articleRowMapper(), postId);
        return query.stream().findAny().orElseThrow(() -> new NoSuchArticle("없는 게시글입니다."));
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            ArticleResult articleResult = new ArticleResult();
            articleResult.setPostId(rs.getLong("id"));
            articleResult.setWriter(rs.getString("writer"));
            articleResult.setTitle(rs.getString("title"));
            articleResult.setContent(rs.getString("content"));
            articleResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            articleResult.setNumOfComment(rs.getLong("numOfComment"));

            return Article.from(articleResult);
        };
    }
}
