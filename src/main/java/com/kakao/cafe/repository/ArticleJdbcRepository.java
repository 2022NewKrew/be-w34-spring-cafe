package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
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
                "insert into article(id, writer, title, contents, createTime) values ( ?, ?, ?, ?, ?)",
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents(),
                Timestamp.valueOf(article.getCreateTime())
        );
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM article ORDER BY id DESC",
                mapper
        );
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select id, writer, title, contents, createTime time from article where id = ?",
                mapper,
                id
        ));
    }


    private final RowMapper<Article> mapper = (rs, rowNum) -> {
        Article article = new Article(
                rs.getInt("id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createTime").toLocalDateTime());
        return article;
    };
}
