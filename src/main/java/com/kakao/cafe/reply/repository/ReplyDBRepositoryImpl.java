package com.kakao.cafe.reply.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.domain.ReplyRepository;

@Repository
@RequiredArgsConstructor
public class ReplyDBRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long persist(Reply reply) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> makePersistStatement(conn, reply), generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public List<Reply> findAll(Long articleId) {
        return jdbcTemplate.query(SQL.FIND_BY_ARTICLE_ID.stmt, this::convertToReply, articleId);
    }

    @Override
    public void deleteReply(Long id) {
        jdbcTemplate.update(SQL.DELETE_ARTICLE.stmt, id);

    }

    private PreparedStatement makePersistStatement(Connection conn, Reply reply) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(SQL.CREATE.stmt, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, reply.getArticleId());
        statement.setLong(2, reply.getAuthorId());
        statement.setString(3, reply.getAuthorStringId());
        statement.setString(4, reply.getContents());
        statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        statement.setBoolean(6, true);
        return statement;
    }

    private Reply convertToReply(ResultSet rs, int rowNum) throws SQLException {
        return Reply.builder()
                    .articleId(rs.getLong("id"))
                    .id(rs.getLong("id"))
                    .articleId(rs.getLong("article_id"))
                    .authorId(rs.getLong("author_id"))
                    .authorStringId(rs.getString("author_string_id"))
                    .contents(rs.getString("content"))
                    .writeTime(rs.getTimestamp("write_date").toLocalDateTime())
                    .isAvailable(rs.getBoolean("is_available"))
                    .build();
    }

    private enum SQL {
        FIND_BY_ARTICLE_ID(
                "SELECT id, article_id, author_id, author_string_id, content, write_date, is_available FROM REPLY WHERE article_id = ? AND is_available = true"),
        CREATE("INSERT INTO REPLY (article_id, author_id, author_string_id, content, write_date, is_available) VALUES (?, ?, ?, ?, ?, ?)"),
        DELETE_ARTICLE("UPDATE REPLY SET is_available = 0 WHERE id = ?");

        public final String stmt;

        SQL(String stmt) {
            this.stmt = stmt;
        }
    }
}
