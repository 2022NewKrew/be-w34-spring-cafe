package com.kakao.cafe.dao;

import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.dto.CommentPostUserDbDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CommentPostUserDbDto findById(long id) {

        String query =
                "SELECT COMMENTS.ID, COMMENTS.TEXT, POSTS.ID, POSTS.TITLE, POSTS.CONTENTS, USERS.ID, USERS.PASSWORD, USERS.EMAIL, USERS.NAME " +
                        "FROM COMMENTS " +
                        "JOIN POSTS " +
                        "ON COMMENTS.POST_ID = POSTS.ID " +
                        "JOIN USERS " +
                        "ON COMMENTS.USER_ID = USERS.ID " +
                        "WHERE COMMENTS.ID = ?;";

        return jdbcTemplate.queryForObject(query, (rs, count) -> new CommentPostUserDbDto.Builder()
                .id(rs.getLong("COMMENTS.ID"))
                .text(rs.getString("COMMENTS.TEXT"))
                .postId(rs.getLong("POSTS.ID"))
                .title(rs.getString("POSTS.TITLE"))
                .contents(rs.getString("POSTS.CONTENTS"))
                .userId(rs.getString("USERS.ID"))
                .password(rs.getString("USERS.PASSWORD"))
                .email(rs.getString("USERS.EMAIL"))
                .name(rs.getString("USERS.NAME"))
                .build(), id);

    }

    public List<CommentPostUserDbDto> findAll(long postId) {
        String query =
                "SELECT COMMENTS.ID, COMMENTS.TEXT, POSTS.ID, POSTS.TITLE, POSTS.CONTENTS, USERS.ID, USERS.PASSWORD, USERS.EMAIL, USERS.NAME " +
                        "FROM COMMENTS " +
                        "JOIN POSTS " +
                        "ON COMMENTS.POST_ID = POSTS.ID " +
                        "JOIN USERS " +
                        "ON COMMENTS.USER_ID = USERS.ID " +
                        "WHERE POSTS.ID = ?;";

        return jdbcTemplate.query(query, (rs, count) -> new CommentPostUserDbDto.Builder()
                .id(rs.getLong("COMMENTS.ID"))
                .text(rs.getString("COMMENTS.TEXT"))
                .postId(rs.getLong("POSTS.ID"))
                .title(rs.getString("POSTS.TITLE"))
                .contents(rs.getString("POSTS.CONTENTS"))
                .userId(rs.getString("USERS.ID"))
                .password(rs.getString("USERS.PASSWORD"))
                .email(rs.getString("USERS.EMAIL"))
                .name(rs.getString("USERS.NAME"))
                .build(), postId);
    }


    public int insert(CommentDbDto commentDbDto) {
        String query = "INSERT INTO COMMENTS (POST_ID, USER_ID, TEXT) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, commentDbDto.getPostId(), commentDbDto.getUserId(), commentDbDto.getText());
    }

    public int update(CommentDbDto commentDbDto) {
        String query = "UPDATE COMMENTS SET POST_ID = ?, USER_ID = ?, TEXT = ? WHERE ID = ?";
        return jdbcTemplate.update(query, commentDbDto.getPostId(), commentDbDto.getUserId(), commentDbDto.getText(), commentDbDto.getId());
    }

    public int deleteById(long id) {
        String query = "DELETE FROM COMMENTS WHERE ID = ?";
        return jdbcTemplate.update(query, id);
    }

    public int deleteAll(long postId) {
        String query = "DELETE FROM COMMENTS WHERE POST_ID = ?;";
        return jdbcTemplate.update(query, postId);
    }


}
