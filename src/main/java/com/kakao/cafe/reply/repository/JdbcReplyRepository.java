package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Reply save(Reply reply) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO REPLY (CONTENT, ARTICLE_ID, MEMBER_ID, CREATE_DATE_TIME) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, reply.getContent());
            ps.setLong(2, reply.getArticleId());
            ps.setLong(3, reply.getMemberId());
            ps.setObject(4, reply.getCreateDateTime());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();

        if (id != null) {
            reply.setId(id.longValue());
        }

        return reply;
    }

    @Override
    public Optional<Reply> findOne(Long id) {
        String sql = "SELECT ID, CONTENT, ARTICLE_ID, MEMBER_ID, CREATE_DATE_TIME FROM REPLY WHERE ID = ?";
        return jdbcTemplate.query(sql, replyRowMapper(), id).stream().findAny();
    }

    @Override
    public List<Reply> findByArticle(Long articleId) {
        String sql = "SELECT ID, CONTENT, ARTICLE_ID, MEMBER_ID, CREATE_DATE_TIME FROM REPLY WHERE ARTICLE_ID = ?";
        return jdbcTemplate.query(sql, replyRowMapper(), articleId);
    }

    @Override
    public List<Reply> findAll() {
        String sql = "SELECT ID, CONTENT, ARTICLE_ID, MEMBER_ID, CREATE_DATE_TIME FROM REPLY";
        return jdbcTemplate.query(sql, replyRowMapper());
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE REPLY SET DELETED = TURE WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Reply> replyRowMapper() {
        return ((rs, rowNum) -> Reply.builder()
                .id(rs.getLong("ID"))
                .content(rs.getString("CONTENT"))
                .articleId(rs.getLong("ARTICLE_ID"))
                .memberId(rs.getLong("MEMBER_ID"))
                .createDateTime(rs.getObject("CREATE_DATE_TIME", LocalDateTime.class))
                .build());
    }
}
