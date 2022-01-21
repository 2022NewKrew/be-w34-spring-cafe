package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@RequiredArgsConstructor
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_QUERY = "insert into articles(writer, title, contents) values(?, ?, ?)";
    private static final String SELECT_QUERY = "select * from articles where id=? and deleted=false";
    private static final String SELECT_ALL_QUERY = "select * from articles where deleted=false";
    private static final String UPDATE_QUERY = "update articles set title=?, contents=? where id=?";
    private static final String DELETE_QUERY = "update articles set deleted=1 where id=?";

    @Override
    public void save(Article article) {
        jdbcTemplate.update(INSERT_QUERY,
            article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public Article findById(Long id) {
        return jdbcTemplate.query(SELECT_QUERY, mapper, id).get(0);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, mapper);
    }

    @Override
    public void update(Long id, Article article) {
        jdbcTemplate.update(UPDATE_QUERY,
            article.getTitle(), article.getContents(), id.toString());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    static RowMapper<Article> mapper = (rs, rowNum) ->
        Article.builder()
            .id((long) rs.getInt("id"))
            .title(rs.getString("title"))
            .writer(rs.getString("writer"))
            .contents(rs.getString("contents"))
            .build();
}
