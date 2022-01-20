package com.kakao.cafe.module.repository;

import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addReply(Reply reply) {
        String query = "INSERT INTO REPLY (article_id, author_id, comment) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setLong(1, reply.getArticleId());
            preparedStatement.setLong(2, reply.getAuthorId());
            preparedStatement.setString(3, reply.getComment());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }

    @Override
    public List<ReplyReadDto> findRepliesByArticleId(Long id) {
        String query = "SELECT REPLY.id, ARTICLE.id, USERS.id, USERS.name, REPLY.created, REPLY.comment" +
                " FROM REPLY" +
                " INNER JOIN ARTICLE" +
                " ON REPLY.article_id = ARTICLE.id" +
                " INNER JOIN USERS" +
                " ON REPLY.author_id = USERS.id" +
                " WHERE ARTICLE.id = ?" +
                " AND REPLY.status = true" +
                " ORDER BY REPLY.created";
        return jdbcTemplate.query(query, mapRowReplies(), id);
    }

    @Override
    public Reply findReplyById(Long id) {
        return jdbcTemplate.query("SELECT * FROM REPLY WHERE id = ?", mapRowReply(), id).stream().findAny()
                .orElseThrow(() -> new NoSuchDataException("해당하는 댓글이 없습니다."));
    }

    @Override
    public void deleteReply(Long id) {
        jdbcTemplate.update("DELETE FROM REPLY WHERE id = ?", id);
    }

    private RowMapper<ReplyReadDto> mapRowReplies() {
        return ((rs, rowNum) -> new ReplyReadDto(
                rs.getLong("REPLY.id"),
                rs.getLong("ARTICLE.id"),
                rs.getLong("USERS.id"),
                rs.getString("USERS.name"),
                rs.getTimestamp("REPLY.created").toLocalDateTime(),
                rs.getString("REPLY.comment")
        ));
    }

    private RowMapper<Reply> mapRowReply() {
        return ((rs, rowNum) -> new Reply(
                rs.getLong("id"),
                rs.getLong("article_id"),
                rs.getLong("author_id"),
                rs.getString("comment"),
                rs.getTimestamp("created").toLocalDateTime(),
                rs.getBoolean("status")
        ));
    }
}
