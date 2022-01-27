package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into articles(writer, title, contents, createTime) values ( ?, ?, ?, ? )";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getUser().getUserName());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            ps.setObject(4, Timestamp.valueOf(article.getCreateTime()));
            return ps;
        }, keyHolder);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "select * from articles join users on articles.writer = users.userName order by article_id desc",
                mapper
        );
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return jdbcTemplate.query(
                "select * from articles join users on articles.writer = users.userName where article_id = ?",
                mapper,
                id
        ).stream().findAny();
    }


    public void update(Article article) {
        jdbcTemplate.update(
                "update articles set writer = ?, title = ?, contents = ? where article_id = ?",
                article.getUser().getUserName(),
                article.getTitle(),
                article.getContents(),
                article.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update(
                "delete articles where article_id = ?",
                id
        );
    }


    private final RowMapper<Article> mapper = (rs, rowNum) -> Article.builder()
            .id(rs.getInt("article_id"))
            .user(User.builder()
                    .user_id(rs.getInt("user_id"))
                    .userId(rs.getString("userID"))
                    .password(rs.getString("password"))
                    .userName(rs.getString("userName"))
                    .email(rs.getString("email"))
                    .build())
            .title(rs.getString("title"))
            .contents(rs.getString("contents"))
            .createTime(rs.getTimestamp("createTime").toLocalDateTime())
            .build();
}
