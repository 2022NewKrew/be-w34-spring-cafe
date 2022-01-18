package com.kakao.cafe.web.repository.article;

import com.kakao.cafe.web.domain.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<>(Article.class);

    public JdbcTemplateArticleRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(Article article) {
        String sql = "insert into articles (`title`, `content`, `writer`) values(:title,:content,:writer)";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(article);
        jdbcTemplate.update(sql, paramSource);
    }

    @Override
    public Optional<Article> findById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        List<Article> result = jdbcTemplate.query("select * from articles where `id` = :id", param, articleRowMapper);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from articles", articleRowMapper);
    }
}
