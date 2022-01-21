package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringJdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReplyMapper replyMapper;

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update("insert into REPLY(article_seq_id,writer_id,comment) values ( ?,?,? )",
                reply.getArticleId(), reply.getWriter().getUserId(), reply.getComment());
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        return jdbcTemplate.query("select * from REPLY r JOIN USERS u ON r.writer_id=u.user_id where r.article_seq_id=?", replyMapper, articleId);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from REPLY where reply_seq_id = ?", id);
    }

    @Override
    public Optional<Reply> findById(Long id) {
        List<Reply> replies = jdbcTemplate.query("select * from REPLY r join USERS u ON r.writer_id=u.user_id where r.reply_seq_id=?", replyMapper, id);
        return replies.stream().findAny();
    }

}
