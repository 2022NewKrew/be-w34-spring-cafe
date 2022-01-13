package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticleRepository implements Repository<Article, ArticleDTO, Integer> {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", articleDTO.getWriter());
        parameters.put("title", articleDTO.getTitle());
        parameters.put("contents", articleDTO.getContents());
        parameters.put("date", articleDTO.getDate());
        parameters.put("comment_size", articleDTO.getCommentSize());
        parameters.put("parent", articleDTO.getParent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        articleDTO.setId(key.intValue());

        return new Article(articleDTO);
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return null;
    }
}
