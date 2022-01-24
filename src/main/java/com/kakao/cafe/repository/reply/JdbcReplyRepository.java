package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder;

    @Autowired
    public JdbcReplyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        keyHolder = new GeneratedKeyHolder();
    }


    @Override
    public Reply save(Reply reply) {
        final String INSERT_REPLY = "INSERT INTO `REPLY`(userId, articleId, comments, createdAt) VALUES (:userId, :articleId, :comments, :createdAt)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(reply);
        namedParameterJdbcTemplate.update(INSERT_REPLY, namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        reply.setId(id);
        return reply;
    }

    @Override
    public long delete(long id) {
        final String DELETE_REPLY = "DELETE FROM `REPLY` WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_REPLY, namedParameters);
        return id;
    }

    @Override
    public long deleteByArticleId(long articleId) {
        final String DELETE_REPLY = "DELETE FROM `REPLY` WHERE articleId = :articleId";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("articleId", articleId);
        return namedParameterJdbcTemplate.update(DELETE_REPLY, namedParameters);
    }

    @Override
    public List<Reply> findAllReply(long articleId) {
        final String FIND_ALL_REPLY = "SELECT * FROM `REPLY` WHERE articleId = :articleId";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("articleId", articleId);
        return namedParameterJdbcTemplate.query(FIND_ALL_REPLY, namedParameters, replyRowMapper());
    }

    @Override
    public Optional<String> findUserNicknameById(Long id) {
        final String FIND_NICKNAME_BY_USERID = "SELECT USER.nickname FROM `REPLY` JOIN `USER` ON USER.id = REPLY.userId WHERE REPLY.id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    FIND_NICKNAME_BY_USERID, namedParameters, String.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) ->
                Reply.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getLong("userId"))
                        .articleId(rs.getLong("articleId"))
                        .comments(rs.getString("comments"))
                        .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                        .build();
    }
}
