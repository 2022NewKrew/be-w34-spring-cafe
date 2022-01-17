package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.controller.dto.ArticleSaveForm;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.NoSuchArticle;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.DBConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private static AtomicLong id = new AtomicLong(0L);
    private final JdbcTemplate jdbcTemplate;

    public ArticleJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(ArticleSaveForm article) {
        long articleId = id.incrementAndGet();

        jdbcTemplate.update("insert into " + DBConfig.ARTICLE_DB + " values(?,?,?,?,?,?)"
                , articleId
                , article.getWriter()
                , article.getTitle()
                , article.getContent()
                , LocalDateTime.now()
                , 0);
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
            Article article = new Article();
            article.setArticleId(rs.getLong("id"));
            article.setWriter(rs.getString("writer"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            article.setNumOfComment(rs.getLong("numOfComment"));

            return article;
        };
    }
}
