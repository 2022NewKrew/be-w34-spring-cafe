package com.kakao.cafe.dao;

import com.kakao.cafe.dto.CommentDbDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CommentDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CommentDbDto findById(long id) {
        String query = "SELECT * FROM COMMENTS WHERE ID = ?";

        return jdbcTemplate.queryForObject(query, (rs, count) -> new CommentDbDto(
                rs.getLong("ID"),
                rs.getLong("POST_ID"),
                rs.getString("USER_ID"),
                rs.getString("TEXT")
        ), id);
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


}
