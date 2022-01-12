package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into ARTICLE (WRITER, TITLE, CONTENTS) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getWriter());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jdbcTemplate.
                query("select * from article where id = ?", articleRowMapper(), id).
                stream().findAny();

    }
    private RowMapper<Article> articleRowMapper(){
        return (rs, rowNum) -> {
            Article article = Article.builder().
                    id(rs.getLong("id")).
                    writer(rs.getString("writer")).
                    title(rs.getString("title")).
                    contents(rs.getString("contents")).
                    build();
            return article;
        };
    }
}
