package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public class ArticleJdbcRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Article article){
        jdbcTemplate.update(
                "insert into article(article_id, writer, title, contents, createTime) values ( ?, ?, ?, ?, ?)",
                article.getId(),
                article.getUser().getUserName(),
                article.getTitle(),
                article.getContents(),
                Timestamp.valueOf(article.getCreateTime())
        );
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "select * from article join users on article.writer = users.userName order by article_id desc",
                mapper
        );
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return jdbcTemplate.query(
                "select * from article join users on article.writer = users.userName where article_id = ?",
                mapper,
                id
        ).stream().findAny();
    }


    private final RowMapper<Article> mapper = (rs, rowNum) -> Article.builder()
            .id(rs.getInt("article_id"))
            .user(User.builder()
                    .id(rs.getInt("id"))
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
