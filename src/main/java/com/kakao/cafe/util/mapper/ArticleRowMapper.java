package com.kakao.cafe.util.mapper;

import com.kakao.cafe.dto.ArticleDbDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleRowMapper implements RowMapper<ArticleDbDto> {
    public ArticleDbDto mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new ArticleDbDto(
                resultSet.getLong("ARTICLE_ID"),
                resultSet.getString("NAME"),
                resultSet.getDate("WRITE_TIME"),
                resultSet.getString("TITLE"),
                resultSet.getString("CONTENTS")
        );
    }
}
