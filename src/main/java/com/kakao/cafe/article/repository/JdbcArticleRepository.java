package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Article save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO ARTICLE (TITLE, AUTHOR, CONTENT, CREATE_DATE) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getAuthor());
            ps.setString(3, article.getContent());
            ps.setObject(4, article.getCreateDate());
            return ps;
        }, keyHolder);

        Long id = (Long) keyHolder.getKey();

        article.setId(id);
        return article;
    }

    @Override
    public Optional<Article> findOne(Long id) {
        String sql = "SELECT ID, TITLE, AUTHOR, CONTENT, CREATE_DATE FROM ARTICLE WHERE ID = ?";
        return jdbcTemplate.query(sql, articleRowMapper(), id).stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT ID, TITLE, AUTHOR, CONTENT, CREATE_DATE FROM ARTICLE";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    @Override
    public void update(Long id) {

    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> Article.builder()
                .id(rs.getLong("ID"))
                .title(rs.getString("TITLE"))
                .author(rs.getString("AUTHOR"))
                .content(rs.getString("CONTENT"))
                .createDate(rs.getObject("CREATE_DATE", LocalDate.class))
                .build();
    }
}
