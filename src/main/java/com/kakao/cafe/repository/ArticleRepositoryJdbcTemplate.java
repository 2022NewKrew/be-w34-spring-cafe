package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArticleRepositoryJdbcTemplate implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositoryJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Article> selectAll() {
        return jdbcTemplate.query("SELECT * FROM article ORDER BY postTime DESC", articleRowMapper());
    }

    @Override
    public Optional<Article> selectByKey(Long key) {
        List<Article> result = jdbcTemplate.query("SELECT * from article where key = ?", articleRowMapper(), key);
        return result.stream().findAny();
    }

    @Override
    public Long insert(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("key");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", article.getAuthor());
        parameters.put("title", article.getTitle());
        parameters.put("content", article.getContent());
        parameters.put("postTime", article.getPostTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key.longValue();
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = Article.builder()
                    .key(rs.getLong("key"))
                    .author(rs.getString("author"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .postTime(rs.getTimestamp("postTime").toLocalDateTime())
                    .build();
            return article;
        };
    }
}
