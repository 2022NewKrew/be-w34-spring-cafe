package com.kakao.cafe.articles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@PropertySource("classpath:sql/article.xml")
public class JdbcArticleRepository implements ArticleRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${article.save}")
    private String SAVE_SQL;
    @Value("${article.findById}")
    private String FIND_BY_ID_SQL;
    @Value("${article.findAll}")
    private String FIND_ALL_SQL;
    @Value("${article_content.save}")
    private String ARTICLE_CONTENT_SAVE_SQL;
    @Value("${article.count}")
    private String COUNT_SQL;


    private static final RowMapper<Article> rowMapper = (resultSet, rowNum) -> new Article(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            new ArticleContent(resultSet.getString("body")),
            resultSet.getString("writer"),
            resultSet.getLong("writer_id")
    );

    public JdbcArticleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article article) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", article.getId());
        params.put("title", article.getTitle());
        params.put("writer", article.getWriter());
        params.put("writer_id", article.getWriterId());

        jdbcTemplate.update(SAVE_SQL, params);

        params.clear();
        params.put("body", article.getContent().getBody());
        params.put("article_id", article.getId());

        jdbcTemplate.update(ARTICLE_CONTENT_SAVE_SQL, params);

        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, params, rowMapper));
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, rowMapper);
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject(COUNT_SQL, new HashMap<>(), int.class);
    }
}
