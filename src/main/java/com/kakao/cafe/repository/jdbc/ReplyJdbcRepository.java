package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.util.TimeStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyJdbcRepository  implements ReplyRepository {
    private static final String STORE_SQL =
            "INSERT INTO REPLIES(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE) VALUES(?, ?, ?, ?)";
    private static final String RETRIEVE_SQL =
            "SELECT * FROM REPLIES WHERE REPLY_ID=? AND DELETED=FALSE";
    private static final String DELETE_SQL =
            "UPDATE REPLIES SET DELETED=TRUE WHERE REPLY_ID=?";
    private static final String TO_LIST_SQL =
            "SELECT * FROM REPLIES WHERE ARTICLE_ID=? AND DELETED=FALSE";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(ReplyCreateCommand rcc) {
        jdbcTemplate.update(STORE_SQL,
                rcc.getArticleId(),
                rcc.getWriterId(),
                rcc.getContents(),
                TimeStringParser.parseTimeToString(LocalDateTime.now()));
    }

    @Override
    public Optional<Reply> retrieve(Long replyId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(RETRIEVE_SQL, replyRowMapper(), replyId));
    }

    @Override
    public void delete(Long replyId) { jdbcTemplate.update(DELETE_SQL, replyId); }

    @Override
    public List<Reply> toList(Long articleId) {
        return jdbcTemplate.query(TO_LIST_SQL, replyRowMapper(), articleId);
    }

    public RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> new Reply(
                rs.getLong("REPLY_ID"),
                rs.getLong("ARTICLE_ID"),
                rs.getString("WRITER_ID"),
                rs.getString("CONTENT"),
                TimeStringParser.parseStringToTime(rs.getString("CREATED_DATE"))
        );
    }
}
