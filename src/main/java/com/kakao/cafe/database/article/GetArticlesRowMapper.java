package com.kakao.cafe.database.article;

import com.kakao.cafe.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GetArticlesRowMapper implements RowMapper<Article> {
    private Logger logger = LoggerFactory.getLogger(GetArticlesRowMapper.class);
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.info("SQL Result: {}", rs);
        return new Article(0,"123","sad","Asd");
    }
}
