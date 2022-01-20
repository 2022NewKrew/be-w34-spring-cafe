package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.ReplyListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateReplyRepository implements ReplyRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Reply reply) {
        final String sql = "insert into `reply` (`question_id`, `user_id`, `contents`, `created_date_time`) values(?,?,?,?)";
        Object[] parameters ={
                reply.getQuestionId(),
                reply.getUserId(),
                reply.getContents(),
                reply.getCreatedDateTime(),
        };
        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<ReplyListResponse> findAllByQuestionId(Long questionId) {
        final String sql = "select " +
                "r.id as id , r.question_id as question_id, r.user_id as user_id, u.nickname as writer, " +
                "r.contents as contents, r.created_date_time as created_date_time " +
                "from `reply` r join `user` u " +
                "on r.user_id = u.id " +
                "where question_id = ? and r.is_deleted = false";
        return jdbcTemplate.query(sql, replyListResponseRowMapper(), questionId);
    }

    @Override
    public void updateIsDeleted(Reply reply) {
        final String sql = "update `reply` set `is_deleted` = true where `id`=? and `user_id`=?";
        Object[] parameters = {
                reply.getId(),
                reply.getUserId(),
        };
        jdbcTemplate.update(sql, parameters);
    }

    private RowMapper<ReplyListResponse> replyListResponseRowMapper(){
        return (rs, rowNum) -> ReplyListResponse.builder()
                .replyId(rs.getLong("id"))
                .questionId(rs.getLong("question_id"))
                .userId(rs.getLong("user_id"))
                .writer(rs.getString("writer"))
                .contents(rs.getString("contents"))
                .createdDateTime(rs.getTimestamp("created_date_time").toLocalDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm")))
                .build();

    }
}
