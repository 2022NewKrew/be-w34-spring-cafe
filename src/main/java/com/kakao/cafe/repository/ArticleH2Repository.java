package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Primary
@Repository
public class ArticleH2Repository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Article article) {
        String sql = "insert into article (writer, title, contents) values(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "ID" });
            ps.setString(1, article.getWriter());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
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
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 Id 입니다");
                });
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

    @Override
    public void deleteById(String Id) {
        String sql = "delete from article where id = ?";
        jdbcTemplate.update(sql, Id);
    }

    @Override
    public void deleteByWriter(String writer) {
        String sql = "delete from article where writer = ?";
        jdbcTemplate.update(sql, writer);
    }

    @Override
    public void update(Article article) {
        String sql = "UPDATE ARTICLE SET contents = ? WHERE ID = ?";
        jdbcTemplate.update(sql, article.getContents() ,article.getId());
    }
}
