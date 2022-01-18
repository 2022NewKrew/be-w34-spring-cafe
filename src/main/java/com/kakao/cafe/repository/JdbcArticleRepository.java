package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Optional<Article> findById(int id) {
        List<Article> result = jdbcTemplate.query("select * from article where id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public int save(Article article) {
        return jdbcTemplate.update("insert into article (title, content, writer, date) values (?, ?, ?, ?)",
                article.getTitle(),
                article.getContent(),
                article.getWriter(),
                LocalDateTime.now());
    }

    @Override
    public int update(Article article) {
        return jdbcTemplate.update("update article set title = ?, content = ?, date = ? where id = ?",
                article.getTitle(),
                article.getContent(),
                LocalDateTime.now(),
                article.getId());
    }

    @Override
    public int delete(Article article) {
        return jdbcTemplate.update("delete from article where id = ?",
                article.getId());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("writer"),
                    rs.getObject("date", LocalDateTime.class));
            return article;
        };
    }

}
