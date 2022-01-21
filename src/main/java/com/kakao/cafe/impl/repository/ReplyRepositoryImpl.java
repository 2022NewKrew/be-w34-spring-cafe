package com.kakao.cafe.impl.repository;

import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository("replyRepository")
public class ReplyRepositoryImpl implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public long insertReply(ReplyDTO reply) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into  Reply (writerID, articleID, contents) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, reply.getWriterID());
            ps.setLong(2, reply.getArticleID());
            ps.setString(3, reply.getContents());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<ReplyDTO> getArticleReplies(long articleId, long lastReplyId) {
        return jdbcTemplate.query("select R.id,writerID,userId writerName,articleID,contents,date_format(R.time,'%Y-%m-%d %H:%i') time from Reply R join User U on R.writerID = U.id where R.id > ? and articleID = ? and isDelete = FALSE order by R.id ",
                getRowMapper(), lastReplyId, articleId
        );
    }

    @Override
    public ReplyDTO getReplyById(long replyId) {
        return jdbcTemplate.queryForObject("select R.id,writerID,userId writerName,articleID,contents,date_format(R.time,'%Y-%m-%d %H:%i') time from Reply R join User U on R.writerID = U.id where R.id = ? and isDelete = FALSE",
                getRowMapper(), replyId
        );
    }

    public int getOtherUserRepliesCount(long articleId, long userId) {
        return jdbcTemplate.queryForObject("select count(id) from Reply where isDelete = false and articleID = ? and writerID != ?", int.class, articleId, userId);
    }

    @Override
    public int deleteReply(long replyId) {
        return jdbcTemplate.update("update Reply set isDelete = TRUE where id = ?", replyId);
    }

    @Override
    public int deleteAllReplies(long articleId) {
        return jdbcTemplate.update("update Reply set isDelete = TRUE where articleID = ?", articleId);
    }

    RowMapper<ReplyDTO> getRowMapper() {
        return (rs, rowNum) -> {
            ReplyDTO reply = new ReplyDTO(
                    rs.getLong("id"),
                    rs.getString("writerName"),
                    rs.getLong("articleID"),
                    rs.getString("contents"),
                    rs.getString("time")
            );
            reply.setWriterID(rs.getLong("writerID"));
            return reply;
        };
    }
}
