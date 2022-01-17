package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {
        jdbcTemplate.update("INSERT INTO articles (writer, title, contents) VALUES (?, ?, ?)",
                article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public Optional<Article> findByArticleId(Long id) {
        List<Article> result =  jdbcTemplate.query("SELECT * FROM articles WHERE id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM articles", articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper(){
        return (rs, rowNum) -> {
            return Article.builder()
                    .articleId(rs.getLong("id"))
                    .writer(rs.getString("writer"))
                    .title(rs.getString("title"))
                    .contents(rs.getString("contents"))
                    .build();
        };
    }
}
