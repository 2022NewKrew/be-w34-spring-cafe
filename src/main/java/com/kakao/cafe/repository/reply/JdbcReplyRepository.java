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

import static com.kakao.cafe.util.SqlReply.*;

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
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(reply);
        namedParameterJdbcTemplate.update(INSERT_REPLY.query(), namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        reply.setId(id);
        return reply;
    }

    @Override
    public long delete(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_REPLY.query(), namedParameters);
        return id;
    }

    @Override
    public long deleteByArticleId(long articleId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("articleId", articleId);
        return namedParameterJdbcTemplate.update(DELETE_REPLY_BY_ARTICLEID.query(), namedParameters);
    }

    @Override
    public List<Reply> findAllReply(long articleId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("articleId", articleId);
        return namedParameterJdbcTemplate.query(FIND_ALL_REPLY.query(), namedParameters, replyRowMapper());
    }

    @Override
    public Optional<String> findUserNicknameById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    FIND_NICKNAME_BY_USERID.query(), namedParameters, String.class));
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
