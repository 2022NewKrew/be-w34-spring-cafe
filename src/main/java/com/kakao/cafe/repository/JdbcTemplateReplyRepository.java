package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.util.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateReplyRepository implements ReplyInterface {
    private static final String ALL_OF_REPLY = "select id, content, date, u.name as writer, " +
            "r.writerid as writerid, articleid, deleted from replies as r join users as u " +
            "where r.writerid = u.userid AND deleted=false";
    private static final String ORDERED = " order by id desc";
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateReplyRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        return jdbcTemplate.query(ALL_OF_REPLY + " AND articleid = ? " + ORDERED, replyRowMapper(), articleId);
    }

    @Override
    public void deleteAllReplyOnArticle(Long articleId) {
        jdbcTemplate.update("update replies set deleted=true where articleid = ?", articleId);
    }

    @Override
    public Reply save(Reply reply) {
        reply.setDate(DateUtils.getLocalDateTimeNow());

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("replies").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = makeParameters(reply);
        Number id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        reply.setId(id.longValue());
        return reply;
    }

    @Override
    public Optional<Reply> findById(Long id) {
        List<Reply> reply = jdbcTemplate.query(ALL_OF_REPLY + " AND id = ?" + ORDERED, replyRowMapper(), id);
        return reply.stream().findAny();
    }

    @Override
    public Optional<Reply> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Reply> findAll() {
        return jdbcTemplate.query(ALL_OF_REPLY + ORDERED, replyRowMapper());
    }

    @Override
    public Reply update(Reply reply) {
        return null;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("update replies set deleted = true where id = ?", id);
    }

    private RowMapper<Reply> replyRowMapper() {
        return (resultSet, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(resultSet.getLong("id"));
            reply.setContent(resultSet.getString("content"));
            reply.setDate(resultSet.getString("date"));
            reply.setWriter(resultSet.getString("writer"));
            reply.setWriterId(resultSet.getLong("writerid"));
            reply.setArticleId(resultSet.getLong("articleid"));
            reply.setDeleted(resultSet.getBoolean("deleted"));

            return reply;
        };
    }

    private Map<String, Object> makeParameters(Reply reply) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", reply.getId());
        parameters.put("content", reply.getContent());
        parameters.put("date", reply.getDate());
        parameters.put("writer", reply.getWriter());
        parameters.put("writerid", reply.getWriterId());
        parameters.put("articleid", reply.getArticleId());
        parameters.put("deleted", reply.getDeleted());

        return parameters;
    }
}
