package com.kakao.cafe.dao;

import com.kakao.cafe.dao.mapper.ReplyRowMapper;
import com.kakao.cafe.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReplyDao {

    private final ReplyRowMapper replyRowMapper;
    private final JdbcTemplate jdbcTemplate;

    public void save(ReplyVo replyVo) {
        String writer = replyVo.getWriter();
        String comment = replyVo.getComment();
        int articleId = replyVo.getArticleId();
        String date = replyVo.getDate();
        String userId = replyVo.getUserId();
        jdbcTemplate.update("INSERT INTO reply (writer,comment,articleId,date,userId) VALUES ( ?,?,?,?,? )", writer, comment, articleId, date, userId);
    }

    public List<ReplyVo> findAllByArticleId(int articleId) {
        return jdbcTemplate.query("SELECT * FROM reply WHERE articleId = ?",replyRowMapper,articleId);
    }

    public void deleteByReplyId(int replyId) {
        jdbcTemplate.update("DELETE FROM reply WHERE replyId = ?",replyId);
    }
}
