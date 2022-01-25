package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.exceptions.ReplyNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ReplyRepository.class);

    public ReplyRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    public void save(Reply reply) {
        logger.debug("[Jdbc] reply save: {}", reply);
        String sql = "insert into replies(comment, user_id, post_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, reply.getComment(), reply.getUserId(), reply.getPostId());
        logger.debug("[Jdbc] reply save success : {}", reply);
    }

    public List<Reply> findAllByPostId(int postId) {
        logger.debug("[Jdbc] reply findAllByPostId");
        String sql = "select * from replies where post_id = ?";
        List<Reply> replyList = jdbcTemplate.query(sql, (rs, rowNum) -> new Reply(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("post_id"),
                        rs.getString("comment"),
                        rs.getDate("created_at")),
                postId);
        logger.debug("[Jdbc] reply findAllByPostId success: {}", replyList);
        return replyList;
    }

    public Reply findReplyById(int id) {
        logger.debug("[Jdbc] reply findReplyById id : {}", id);
        String sql = "select * from replies where id = ?";

        try {
            Reply reply = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Reply(rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("post_id"),
                            rs.getString("comment"),
                            rs.getDate("created_at")),
                    id);
            logger.debug("[Jdbc] reply findReplyById success : {}", reply);
            return reply;
        } catch (EmptyResultDataAccessException e) {
            throw new ReplyNotFoundException("없는 댓글 입니다");
        }
    }

    public void delete(int id) {
        logger.debug("[Jdbc] reply delete id: {}", id);
        String sql = "delete from replies where id = ?";

        jdbcTemplate.update(sql, id);
        logger.debug("[Jdbc] reply delete success");
    }

    public void deleteAllByPostId(int postId) {
        logger.debug("[Jdbc] reply deleteAllByPostId: {}", postId);
        String sql = "delete from replies where post_id = ?";

        jdbcTemplate.update(sql, postId);
        logger.debug("[Jdbc] reply deleteAllByPostId success: {}", postId);
    }
}
