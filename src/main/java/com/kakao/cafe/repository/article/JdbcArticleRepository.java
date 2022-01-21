package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Article article) {
        jdbcTemplate.update("INSERT INTO articles (writer, title, contents, deleted) VALUES (?, ?, ?, ?)",
                article.getWriter(), article.getTitle(), article.getContents(), article.getDeleted());
    }

    @Override
    public Optional<Article> selectByArticleId(Long id) {
        List<Article> result =  jdbcTemplate.query("SELECT * FROM articles WHERE id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Article> selectAll() {
        return jdbcTemplate.query("SELECT * FROM articles", articleRowMapper()).stream()
                .filter(article -> !article.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update("UPDATE articles SET title=?, contents=?, deleted=? WHERE ID =?",
                article.getTitle(), article.getContents(), article.getDeleted(), article.getArticleId());

    }

    private RowMapper<Article> articleRowMapper(){
        return (rs, rowNum) -> {
            return Article.builder()
                    .articleId(rs.getLong("id"))
                    .writer(rs.getString("writer"))
                    .title(rs.getString("title"))
                    .contents(rs.getString("contents"))
                    .deleted(rs.getBoolean("deleted"))
                    .build();
        };
    }
}
