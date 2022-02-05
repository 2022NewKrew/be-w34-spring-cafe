package com.kakao.cafe.util.mapper;

import com.kakao.cafe.dto.CommentDbDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<CommentDbDto> {
    @Override
    public CommentDbDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new CommentDbDto(
                resultSet.getLong("COMMENT_ID"),
                resultSet.getString("NAME"),
                resultSet.getDate("WRITE_TIME"),
                resultSet.getString("CONTENTS")
        );
    }
}
