package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository implements CrudRepository<Article, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article entity) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO article(writer, title, contents, user_pk)");
        query.append(" VALUES (?, ?, ?, ?)");
        int update = jdbcTemplate.update(query.toString(), entity.getWriter(), entity.getTitle(), entity.getContents(), entity.getUserPk());
        if (update > 0) {
            return entity;
        }

        return null;
    }

    @Override
    public Article update(Article entity) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE article");
        query.append(" SET writer = ?, title = ?, contents = ?, user_pk = ?");
        query.append(" WHERE article.id = ?");
        int update = jdbcTemplate.update(query.toString(), entity.getWriter(), entity.getTitle(), entity.getContents(), entity.getUserPk(), entity.getId());
        if (update > 0) {
            return entity;
        }

        return null;
    }

    @Override
    public Optional<Article> findById(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM article");
        query.append(" WHERE article.id = ? AND article.deleted = FALSE");
        Article article = jdbcTemplate.queryForObject(query.toString(), (rs, rowNum) -> new Article(rs.getInt("id"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getInt("user_pk"), rs.getBoolean("deleted")), id);
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM article");
        query.append(" WHERE article.deleted = FALSE");
        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> new Article(rs.getInt("id"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getInt("user_pk"), rs.getBoolean("deleted")));
    }

    @Override
    public boolean delete(Article entity) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE article");
        query.append(" SET deleted = ?");
        query.append(" WHERE article.id = ?");
        int delete = jdbcTemplate.update(query.toString(),true, entity.getId());
        return delete > 0;
    }
}
