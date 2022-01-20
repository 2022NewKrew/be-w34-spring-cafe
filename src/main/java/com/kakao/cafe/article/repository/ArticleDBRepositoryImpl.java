package com.kakao.cafe.article.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;

@Repository
@Primary
@RequiredArgsConstructor
public class ArticleDBRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long persist(Article article) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> makePersistStatement(conn, article), generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public Optional<Article> find(Long id) {
        List<Article> result = jdbcTemplate.query(SQL.FIND_BY_DB_ID.stmt, this::convertToArticle, id);
        return result.stream().findFirst();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SQL.FIND_ALL.stmt, this::convertToArticle);
    }

    @Override
    public void increaseHit(Long id) {
        jdbcTemplate.update(SQL.INCREASE_HITS.stmt, id);
    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update(SQL.UPDATE_ARTICLE.stmt, article.getTitle(), article.getContents(), article.getId());
    }

    @Override
    public void deleteArticle(Long id) {
        jdbcTemplate.update(SQL.DELETE_ARTICLE.stmt, id);
    }

    private PreparedStatement makePersistStatement(Connection conn, Article article) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(SQL.CREATE.stmt, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, article.getAuthorId());
        statement.setString(2, article.getAuthorStringId());
        statement.setString(3, article.getTitle());
        statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        statement.setString(5, article.getContents());
        statement.setInt(6, 0);
        statement.setBoolean(7, true);
        return statement;
    }

    private Article convertToArticle(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
                                 .id(rs.getLong("id"))
                                 .title(rs.getString("title"))
                                 .authorId(rs.getLong("author"))
                                 .authorStringId((rs.getString("author_string_id")))
                                 .date(rs.getTimestamp("write_date").toLocalDateTime())
                                 .hits(rs.getInt("hits"))
                                 .contents(rs.getString("content"))
                                 .build();
    }

    private enum SQL {
        FIND_BY_DB_ID("SELECT id, title, author, author_string_id, write_date, hits, content FROM ARTICLE WHERE ID = ? AND is_available = true"),
        FIND_ALL("SELECT id, title, author, author_string_id, write_date, hits, content FROM ARTICLE WHERE is_available = true"),
        CREATE("INSERT INTO ARTICLE (author, author_string_id, title, write_date, content, hits, is_available) VALUES (?, ?, ?, ?, ?, ?, ?)"),
        INCREASE_HITS("UPDATE ARTICLE SET hits = hits + 1 WHERE id = ?"),
        UPDATE_ARTICLE("UPDATE ARTICLE SET title = ?, content = ? WHERE id = ?"),
        DELETE_ARTICLE("UPDATE ARTICLE SET is_available = 0 WHERE id = ?");

        public final String stmt;

        SQL(String stmt) {
            this.stmt = stmt;
        }
    }
}
