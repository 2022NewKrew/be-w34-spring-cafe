package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {
        jdbcTemplate.update("insert into articles(writer, title, contents) values(?, ?, ?)",
            article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public Optional<Article> findById(Long id) {
        List<Article> articles = jdbcTemplate.query("select * from articles where id=?", mapper,
            id);
        return articles.size() == 0 ? Optional.empty() : Optional.of(articles.get(0));
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from articles", mapper);
    }
    
    static RowMapper<Article> mapper = (rs, rowNum) ->
        Article.builder()
            .title(rs.getString("title"))
            .writer(rs.getString("writer"))
            .contents(rs.getString("contents"))
            .build();
}
