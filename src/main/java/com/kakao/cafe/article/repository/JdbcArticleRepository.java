package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_QUERY = "insert into articles(writer, title, contents) values(?, ?, ?)";
    private static final String SELECT_QUERY = "select * from articles where id=?";
    private static final String SELECT_ALL_QUERY = "select * from articles";

    @Override
    public void save(Article article) {
        jdbcTemplate.update(INSERT_QUERY,
            article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public Article findById(Long id) {
        return jdbcTemplate.query(SELECT_QUERY, mapper,
            id).get(0);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, mapper);
    }

    static RowMapper<Article> mapper = (rs, rowNum) ->
        Article.builder()
            .title(rs.getString("title"))
            .writer(rs.getString("writer"))
            .contents(rs.getString("contents"))
            .build();
}
