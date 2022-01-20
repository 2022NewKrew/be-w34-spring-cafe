package com.kakao.cafe.article.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    public ArrayList<Article> findAll() {
        List<Article> articles = jdbcTemplate.query(SQL.FIND_ALL.stmt, this::convertToArticle);
        return new ArrayList<Article>(articles);
    }

    @Override
    public void increaseHit(Long id) {
        jdbcTemplate.update(SQL.INCREASE_HITS.stmt, id);
    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update(SQL.UPDATE_ARTICLE.stmt, article.getTitle(), article.getContents(), article.getId());
    }

    private PreparedStatement makePersistStatement(Connection conn, Article article) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(SQL.CREATE.stmt, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, article.getAuthorId());
        statement.setString(2, article.getTitle());
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        statement.setString(4, article.getContents());
        statement.setInt(5, 0);
        return statement;
    }

    private Article convertToArticle(ResultSet rs, int rowNum) throws SQLException {
        Article article = Article.builder()
                                 .id(rs.getLong("id"))
                                 .title(rs.getString("title"))
                                 .authorId(rs.getLong("author"))
                                 .date(rs.getTimestamp("write_date").toLocalDateTime())
                                 .hits(rs.getInt("hits"))
                                 .contents(rs.getString("content"))
                                 .build();
        return article;
    }

    private enum SQL {
        FIND_BY_DB_ID("SELECT * FROM ARTICLE WHERE ID = ?"),
        FIND_ALL("SELECT * FROM ARTICLE"),
        CREATE("INSERT INTO ARTICLE (author, title, write_date, content, hits) VALUES (?, ?, ?, ?, ?)"),
        INCREASE_HITS("UPDATE ARTICLE SET hits = hits + 1 WHERE id = ?"),
        UPDATE_ARTICLE("UPDATE ARTICLE SET title = ?, content = ? WHERE id = ?");

        public final String stmt;

        SQL(String stmt) {
            this.stmt = stmt;
        }
    }
}
