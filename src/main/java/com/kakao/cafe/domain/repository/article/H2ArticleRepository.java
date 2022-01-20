package com.kakao.cafe.domain.repository.article;

import com.kakao.cafe.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class H2ArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {
        if (article.getId() == null) {
            insert(article);
            return;
        }
        update(article);
    }

    public void update(Article article) {
        String sql = "UPDATE `ARTICLE` SET " +
                "title = ?, content = ?, views = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                article.getTitle(), article.getContent(), article.getViews(),
                article.getId());
    }

    public void insert(Article article) {
        String sql = "INSERT INTO `ARTICLE`(author_id, title, content, views, created_at) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, article.getAuthorId(), article.getTitle(), article.getContent(), article.getViews(), article.getCreatedAt());
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT " +
                "a.id, u.id as author_id, u.name as author, a.title, a.content, a.views, a.created_at " +
                "FROM " +
                "ARTICLE a join `USER` u " +
                "ON a.author_id = u.id " +
                "WHERE a.id = ? AND a.deleted = false";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT " +
                        "a.id, u.id as author_id, u.name as author, a.title, a.content, a.views, a.created_at " +
                        "FROM " +
                        "ARTICLE a join `USER` u " +
                        "ON a.author_id = u.id " +
                        "WHERE a.deleted = false"
                , articleRowMapper());
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE `ARTICLE` SET deleted = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public long countRecords() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM `ARTICLE` WHERE deleted=false", Long.class);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setAuthorId(rs.getLong("author_id"));
            article.setAuthor(rs.getString("author"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setViews(rs.getLong("views"));
            article.setCreatedAt(rs.getDate("created_at"));
            return article;
        };
    }

}
