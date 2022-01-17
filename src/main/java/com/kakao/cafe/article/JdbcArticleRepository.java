package com.kakao.cafe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Primary
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        jdbcTemplate.update(
                "INSERT INTO articles(writer,title,content) VALUES (?,?,?)",
                article.getWriter(),
                article.getTitle(),
                article.getContent()
        );
    }

    @Override
    public Optional<Article> findBySeq(long seq) {
        List<Article> results = jdbcTemplate.query(
                "SELECT * FROM articles WHERE seq=?",
                mapper,
                seq
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> results = jdbcTemplate.query(
                "SELECT * FROM articles WHERE title=?",
                mapper,
                title
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM articles ORDER BY seq DESC",
                mapper
        );
    }

    static RowMapper<Article> mapper = (rs, rowNum) ->
            Article.builder()
                    .seq(rs.getLong("seq"))
                    .writer(rs.getString("writer"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .time(rs.getTimestamp("time").toLocalDateTime())
                    .build();

}
