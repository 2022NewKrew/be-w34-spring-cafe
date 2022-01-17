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
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WriteArticleH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ArticleVo article) {
        Map<String, Object> parameters = Map.of("article_writer", article.getWriter(),
                "article_created_at", LocalDateTime.now(),
                "article_title", article.getTitle(),
                "article_contents", article.getContents());

        jdbcTemplate.update(H2ArticleQueryBuilder.insertOne(List.of("article_writer", "article_created_at", "article_title", "article_contents")), parameters);
    }
}
