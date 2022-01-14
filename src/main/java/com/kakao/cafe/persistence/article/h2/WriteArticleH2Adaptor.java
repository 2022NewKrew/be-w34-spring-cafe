package com.kakao.cafe.persistence.article.h2;

import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.WriteArticlePort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class WriteArticleH2Adaptor implements WriteArticlePort {
    private static final String TABLE_NAME = "ARTICLE";
    private static final List<String> FIELDS = List.of("article_id", "article_writer", "article_created_at", "article_title", "article_contents");
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WriteArticleH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ArticleVo article) {
        String INSERT_USER = "INSERT INTO " + TABLE_NAME + " ( " + String.join(", ", FIELDS) + " ) "
                + " VALUES (:writer, :createdAt, :title, :contents)";

        Map<String, Object> parameters = Map.of("writer", article.getWriter(),
                "createdAt", LocalDateTime.now(),
                "title", article.getTitle(),
                "contents", article.getContents());

        jdbcTemplate.update(INSERT_USER, parameters);
    }
}
