package com.kakao.cafe.dao.reply;

import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.reply.ReplyFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReplyStorage implements ReplyDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReplyStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reply addReply(Reply reply) {
        String query = ReplySql.insert(reply);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> connection.prepareStatement(query, new String[]{"id"}), keyHolder);

        return findReplyById(keyHolder.getKey().intValue()).orElseThrow(
                () -> new RuntimeException("Reply를 생성하고 반환하는 과정에서 발생한 예외"));
    }

    @Override
    public void updateReply(Reply reply) {
        jdbcTemplate.update(ReplySql.update(reply));
    }

    @Override
    public List<Reply> getReplies(int articleId) {
        return jdbcTemplate.query(
                ReplySql.getAllReplyNotDeleted(articleId),
                (rs, rowNum) -> toReply(rs));
    }

    @Override
    public void deleteReply(int id) {
        jdbcTemplate.update(ReplySql.delete(id));
    }

    @Override
    public Optional<Reply> findReplyById(int replyId) {
        return jdbcTemplate.query(ReplySql.findReplyById(replyId), (rs, rowNum) -> toReply(rs))
                .stream()
                .findFirst();
    }

    private Reply toReply(ResultSet resultSet) throws SQLException {
        return ReplyFactory.getReply(
                resultSet.getInt("ID"),
                resultSet.getInt("ARTICLE_ID"),
                resultSet.getString("USER_ID"),
                resultSet.getString("COMMENT"),
                resultSet.getTimestamp("CREATE_DATE").toLocalDateTime(),
                resultSet.getBoolean("DELETED")
        );
    }
}
