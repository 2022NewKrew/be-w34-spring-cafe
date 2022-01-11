package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticleH2Repository implements ArticleRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        String sql = "insert into article (writer, title, contents) values(?, ?, ?)" ;
        jdbcTemplate.update(sql, article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public List<Article> getAllQuestions() {
        String sql = "select * from article";
        return jdbcTemplate
                .query(sql, articleRowMapper());
    }

    @Override
    public Article findById(String id) {
        String sql = "select * from article where id = ?";
        return jdbcTemplate
                .query(sql, articleRowMapper(), id)
                .stream()
                .findAny()
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 Id 입니다");});
    }

    private RowMapper<Article> articleRowMapper() {
        return new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setWriter(rs.getString("writer"));
                article.setTitle(rs.getString("title"));
                article.setContents(rs.getString("contents"));
                return article;
            }
        };
    }
}
