package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository{
    private final Logger logger;
    private final JdbcTemplate jdbcTemplate;

    public MemoryArticleRepository(JdbcTemplate jdbcTemplate) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            String sql = "INSERT INTO cafe_article (writer, title, contents)" +
                    "VALUES ((SELECT user_id FROM cafe_user WHERE user_id = ?), ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, articleDTO.getWriter());
            statement.setString(2, articleDTO.getTitle());
            statement.setString(3, articleDTO.getContents());
            return statement;
        }, holder);
        Article article = getArticleById(Objects.requireNonNull(holder.getKey()).longValue());
        logger.info(article.toString());
        return getArticleById(Objects.requireNonNull(holder.getKey()).longValue());
    }

    @Override
    public List<Article> getArticleList() {
        String sql = "SELECT * FROM cafe_article";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Article.class)
        );
    }

    @Override
    public Article getArticleById(long id) {
        String sql = "SELECT * FROM cafe_article WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(Article.class),
                id);
    }
}
