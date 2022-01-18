package com.kakao.cafe.web.reply.repository;

import com.kakao.cafe.web.reply.domain.Reply;
import com.kakao.cafe.web.reply.dto.ReplyCreateDTO;
import com.kakao.cafe.web.reply.dto.ReplyUpdateDTO;
import com.kakao.cafe.web.utility.CombinedSqlParameterSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MemoryReplyRepository implements ReplyRepository {
    private final Logger logger;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Reply> replyRowMapper = new BeanPropertyRowMapper<>(Reply.class);

    public MemoryReplyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Reply createReply(ReplyCreateDTO replyCreateDTO) {
        String sql = "INSERT INTO cafe_reply (article_id, writer, contents, created_time, modified_time, deleted)" +
                " VALUES (:articleId, :writer, :contents, :createdTime, :modifiedTime, false)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        CombinedSqlParameterSource sqlParameterSource = new CombinedSqlParameterSource(replyCreateDTO);
        sqlParameterSource.addValue("createdTime", new Timestamp(System.currentTimeMillis()));
        sqlParameterSource.addValue("modifiedTime", new Timestamp(System.currentTimeMillis()));
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);

        // Return Created Reply
        sql = "SELECT * FROM cafe_reply WHERE id = :id";
        MapSqlParameterSource returnSqlParameterSource = new MapSqlParameterSource();
        returnSqlParameterSource.addValue("id", Objects.requireNonNull(keyHolder.getKey()).longValue());
        return namedParameterJdbcTemplate.queryForObject(sql, returnSqlParameterSource, replyRowMapper);
    }

    @Override
    public List<Reply> getReplyList() {
        String sql = "SELECT * FROM cafe_reply";
        return namedParameterJdbcTemplate.query(sql, replyRowMapper);
    }

    @Override
    public List<Reply> getReplyListByArticleId(long articleId) {
        String sql = "SELECT * FROM cafe_reply WHERE article_id = :articleId";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("articleId", articleId);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, replyRowMapper);
    }

    @Override
    public List<Reply> getReplyListByWriter(String writer) {
        String sql = "SELECT * FROM cafe_reply WHERE writer LIKE :writer";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("writer", writer);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, replyRowMapper);
    }

    @Override
    public Optional<Reply> getReplyById(long id) {
        String sql = "SELECT * FROM cafe_reply WHERE id = :id";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, replyRowMapper).stream().findFirst();
    }

    @Override
    public void deleteReplyById(long id) {
        String sql = "UPDATE cafe_reply SET deleted = true WHERE id = :id";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        namedParameterJdbcTemplate.query(sql, sqlParameterSource, replyRowMapper);
    }

    @Override
    public Reply updateReply(ReplyUpdateDTO replyUpdateDTO) {
        return null;
    }
}
