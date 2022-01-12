package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository{
    private final Logger logger;

    private final JdbcTemplate jdbcTemplate;

    Map<Integer, Article> articleMap;

    public MemoryArticleRepository(JdbcTemplate jdbcTemplate) {
        this.articleMap = new HashMap<>();
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            String sql = "INSERT INTO ARTICLE (WRITER, TITLE, CONTENTS) VALUES (?, ?, ?)";
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, articleDTO.getWriter());
                statement.setString(2, articleDTO.getTitle());
                statement.setString(3, articleDTO.getContents());
                return statement;
            }
        }, holder);

        long primaryKey = holder.getKey().longValue();

/*        jdbcTemplate.update(sql,
                articleDTO.getWriter(),
                articleDTO.getTitle(),
                articleDTO.getContents(),
                keyHolder
        );*/

        return getArticleById(primaryKey);
    }

    @Override
    public List<Article> getArticleList() {
        String sql = "SELECT * FROM ARTICLE";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Article.class)
        );
    }

    @Override
    public Article getArticleById(long id) {
        String sql = "SELECT * FROM ARTICLE WHERE ID = ? ";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id);
    }
}
