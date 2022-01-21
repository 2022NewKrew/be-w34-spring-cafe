package com.kakao.cafe.app.data;

import com.kakao.cafe.app.data.mapper.ReplyRowMapper;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.ReplyDraft;
import com.kakao.cafe.domain.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private final ReplyRowMapper rowMapper = new ReplyRowMapper();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReplyRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reply create(ReplyDraft draft) {
        String sql = "INSERT INTO replies " +
                "(author_id, article_id, content, created_at) " +
                "VALUES (:author_id, :target_id, :content, :created_at)";
        Date createdAt = new Date();
        Map<String, Object> map = new HashMap<>(draft.toMap());
        map.put("created_at", createdAt);
        SqlParameterSource params = new MapSqlParameterSource(map);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, holder);
        long id = holder.getKey().longValue();
        return draft.createReply(id, createdAt);
    }

    @Override
    public List<Reply> list(long targetId) {
        String sql = "SELECT * FROM replies " +
                "INNER JOIN articles ON articles.ID = replies.article_id " +
                "INNER JOIN users ON replies.author_id = users.id " +
                "WHERE replies.article_id = :target_id";
        SqlParameterSource params = new MapSqlParameterSource("target_id", targetId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    @Override
    public Optional<Reply> getById(long targetId) {
        String sql = "SELECT * FROM replies " +
                "INNER JOIN articles ON articles.ID = replies.article_id " +
                "INNER JOIN users ON replies.author_id = users.id " +
                "WHERE replies.id = :target_id";
        SqlParameterSource params = new MapSqlParameterSource("target_id", targetId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM replies WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, params);
    }
}
