package com.kakao.cafe.domain.repository.article;

import com.kakao.cafe.domain.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class H2ArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Article save(Article article) {
        if (article.getId() == 0) {
            String sql = "INSERT INTO `ARTICLE`(author_id, title, content, views, created_at) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, article.getAuthorId(), article.getTitle(), article.getContent(), article.getViews(), article.getCreatedAt());
            return article;
        }

        String sql = "UPDATE `ARTICLE` SET " +
                "author_id = ?, title = ?, content = ?" +
                "WHERE ID = ?";

        jdbcTemplate.update(sql,
                article.getAuthorId(), article.getTitle(), article.getContent(),
                article.getId());

        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT " +
                "a.id, u.id as author_id, u.name as author, a.title, a.content, a.views, a.created_at " +
                "FROM " +
                "ARTICLE a join `USER` u " +
                "ON a.author_id = u.id " +
                "WHERE a.id = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT " +
                "a.id, u.id as author_id, u.name as author, a.title, a.content, a.views, a.created_at " +
                "FROM " +
                "ARTICLE a join `USER` u " +
                "ON a.author_id = u.id ", articleRowMapper());
    }

    @Override
    public long countRecords() {
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("SELECT COUNT(*) FROM ARTICLE");
            }
        }, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getLong(1);
            }
        });
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
