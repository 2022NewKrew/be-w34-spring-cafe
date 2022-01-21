package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository implements MyRepository<Article, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper mapper;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ArticleMapper();
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "select a.id, a.author_id, u.nickname, a.title, a.description, a.deleted " +
                "from article a join users u " +
                "on a.author_id = u.id " +
                "where a.id = ?";

        try {
            Article article = jdbcTemplate.queryForObject(sql, mapper, id);
            return Optional.ofNullable(article);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "select a.id, a.author_id, u.nickname, a.title, a.description, a.deleted " +
                "from article a join users u " +
                "on a.author_id = u.id " +
                "where not a.deleted";

        return jdbcTemplate.query(sql, mapper);
    }

    public Integer getTotalSize() {
        String sql = "select count(*) " +
                "from article " +
                "where not deleted";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Article> findAllByOffset(int offset, int limit) {
        String sql = "select a.id, a.author_id, u.nickname, a.title, a.description, a.deleted " +
                "from (" +
                "   select * " +
                "   from article " +
                "   where not deleted " +
                "   order by id desc " +
                "   limit ?, ?) a join users u " +
                "on a.author_id = u.id " +
                "order by a.id desc";

        return jdbcTemplate.query(sql, mapper, offset, limit);
    }

    @Override
    public void save(Article entity) {
        String sql = "insert into article (author_id, title, description, deleted) values ( ?, ?, ?, ? )";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getAuthorId());
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getDescription());
            ps.setBoolean(4, entity.isDeleted());
            return ps;
        }, keyHolder);
    }

    @Override
    public void update(Article entity) {
        String sql = "update article " +
                "set title = ?, description = ? " +
                "where id = ?";

        jdbcTemplate.update(
                sql,
                entity.getTitle(),
                entity.getDescription(),
                entity.getId()
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "update article " +
                "set deleted = 1 " +
                "where id = ?";

        jdbcTemplate.update(sql, id);
    }

    private static class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Article(
                    rs.getLong("id"),
                    rs.getLong("author_id"),
                    rs.getString("nickname"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBoolean("deleted")
            );
        }
    }
}
