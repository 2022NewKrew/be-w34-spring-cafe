package com.kakao.cafe.replies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@PropertySource("classpath:sql/reply.xml")
public class JdbcReplyRepository implements ReplyRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${reply.save}")
    private String SAVE_SQL;
    @Value("${reply.findById}")
    private String FIND_BY_ID_SQL;
    @Value("${reply.findAllByArticleIdAndStatus}")
    private String FIND_ALL_BY_ARTICLE_ID_AND_STATUS_SQL;
    @Value("${reply.delete}")
    private String DELETE_SQL;

    private static final RowMapper<Reply> rowMapper = (resultSet, rowNum) -> new Reply(
            resultSet.getLong("id"),
            resultSet.getString("content"),
            resultSet.getLong("article_id"),
            resultSet.getLong("writer_id"),
            resultSet.getBoolean("status")
    );

    public JdbcReplyRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Reply save(Reply reply) {
        Map<String, Object> params = new HashMap<>();

        params.put("content", reply.getContent());
        params.put("article_id", reply.getArticleId());
        params.put("writer_id", reply.getWriterId());
        params.put("status", reply.isStatus());

        jdbcTemplate.update(SAVE_SQL, params);

        return reply;
    }

    @Override
    public Optional<Reply> findById(Long id) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", id);

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, params, rowMapper));
    }

    @Override
    public List<Reply> findAllByArticleIdAndStatus(Long articleId, boolean status) {
        Map<String, Object> params = new HashMap<>();

        params.put("article_id", articleId);
        params.put("status", status);

        return jdbcTemplate.query(FIND_ALL_BY_ARTICLE_ID_AND_STATUS_SQL, params, rowMapper);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", id);
        params.put("status", false);

        jdbcTemplate.update(DELETE_SQL, params);
    }
}
