package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final ArticleRowMapper rowMapper = new ArticleRowMapper();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article create(Draft draft) {
        String sql = "INSERT INTO articles " +
                "(owner_id, author, title, content, created_at) " +
                "VALUES (:owner_id, :author, :title, :content, NOW())";
        Date createdAt = new Date();
        SqlParameterSource params = new MapSqlParameterSource(draft.toMap());
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, holder);
        long id = holder.getKey().longValue();
        return draft.createArticle(id, createdAt);
    }

    @Override
    public List<Article> list() {
        String sql = "SELECT * FROM articles " +
                "INNER JOIN users ON articles.owner_id = users.id";
        Map<String, Object> params = Collections.emptyMap();
        return jdbcTemplate.queryForStream(sql, params, rowMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Nullable
    public Article getById(long id) {
        String sql = "SELECT * FROM articles " +
                "INNER JOIN users ON articles.owner_id = users.id " +
                "WHERE articles.id = :id";
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
