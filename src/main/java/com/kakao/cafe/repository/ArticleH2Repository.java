package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static com.kakao.cafe.query.article.H2RepositoryQuery.*;

@Primary
@Repository
public class ArticleH2Repository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Long> save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"ID"});
            ps.setString(1, article.getWriter());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey().longValue());
    }

    @Override
    public List<Article> getAllQuestions() {
        return jdbcTemplate
                .query(selectAllQuery, articleRowMapper());
    }

    @Override
    public Article findById(String id) {
        return jdbcTemplate
                .query(selectByIdQuery, articleRowMapper(), id)
                .stream()
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 Id 입니다");
                });
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setWriter(rs.getString("writer"));
            article.setTitle(rs.getString("title"));
            article.setContents(rs.getString("contents"));
            return article;
        };
    }

    @Override
    public void deleteById(String Id) {
        jdbcTemplate.update(deleteByIdQuery, Id);
    }

    @Override
    public void deleteByWriter(String writer) {
        jdbcTemplate.update(deleteByWriterQuery, writer);
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update(updateQuery, article.getTitle(), article.getContents(), article.getId());
    }
}
