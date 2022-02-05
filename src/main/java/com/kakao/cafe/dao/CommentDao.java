package com.kakao.cafe.dao;

import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.util.mapper.CommentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDao {

    private final JdbcTemplate jdbcTemplate;

    public CommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(CommentInsertDto commentInsertDto) {
        String sql = "INSERT INTO COMMENTS(ARTICLE_ID, USER_ID, WRITE_TIME, CONTENTS) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, commentInsertDto.getArticleId(), commentInsertDto.getUserId(), commentInsertDto.getWriteTime(), commentInsertDto.getContents());
    }

    public List<CommentDbDto> findByArticleId(Long articleId) {
        String sql = "SELECT C.COMMENT_ID, U.NAME, C.WRITE_TIME, C.CONTENTS FROM COMMENTS C INNER JOIN USERS U ON C.USER_ID = U.USER_ID WHERE C.ARTICLE_ID = ? AND C.DELETED = FALSE";
        return jdbcTemplate.query(sql, new CommentRowMapper(), articleId);
    }

    public int countByArticleIdAndUserId(Long articleId, String userId) {
        String sql = "SELECT COUNT(COMMENT_ID) FROM COMMENTS WHERE ARTICLE_ID = ? AND USER_ID <> ? AND DELETED = FALSE";
        return jdbcTemplate.queryForObject(sql, Integer.class, articleId, userId);
    }

    public int delete(Long commentId, Long articleId, String userId) {
        String sql = "UPDATE COMMENTS SET DELETED = TRUE WHERE COMMENT_ID = ? AND ARTICLE_ID = ? AND USER_ID = ?";
        return jdbcTemplate.update(sql, commentId, articleId, userId);
    }

    public int deleteByArticleIdAndUserId(Long articleId, String userId) {
        String sql = "UPDATE COMMENTS SET DELETED = TRUE WHERE ARTICLE_ID = ? AND USER_ID = ? AND DELETED = FALSE";
        return jdbcTemplate.update(sql, articleId, userId);
    }
}
