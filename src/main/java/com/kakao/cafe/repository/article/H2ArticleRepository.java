package com.kakao.cafe.repository.article;

import com.kakao.cafe.config.SpringJdbcConfig;
import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.domain.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class H2ArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Article save(Article article) {
        if(article.getId() == 0){
            String sql = "INSERT INTO `ARTICLE`(AUTHOR, TITLE, CONTENT, VIEWS, CREATED_AT) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, article.getAuthor(), article.getContent(), article.getViews(), article.getContent());
            return article;
        }

        String sql = "UPDATE `ARTICLE` SET " +
                "AUTHOR = ?, TITLE = ?, CONTENT = ?, VIEWS = ?, CREATED_AT = ?" +
                "WHERE ID = ?";

        jdbcTemplate.update(sql,
                article.getAuthor(), article.getTitle(), article.getContent(), article.getViews(), article.getCreatedAt(),
                article.getId());

        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM ARTICLE WHERE ID = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM ARTICLE", articleRowMapper());
    }

    @Override
    public long countRecords() {
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("SELECT COUNT(*) FROM ARTICLE");
            }
        }, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getLong(1);
            }
        });
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setAuthor(rs.getString("author"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setViews(rs.getLong("views"));
            article.setCreatedAt(rs.getDate("created_at"));
            return article;
        };
    }

}
