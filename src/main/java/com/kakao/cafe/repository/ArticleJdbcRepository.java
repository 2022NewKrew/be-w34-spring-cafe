package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ArticleJdbcRepository implements ArticleRepository{

    private final List<Article> articleList = new ArrayList<>();
    private static Integer articleSequence = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Article article){
        article.setId(articleSequence++);
        jdbcTemplate.update(
                "insert into article(id, writer, title, contents) values ( ?, ?, ?, ? )",
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query(
                "SELECT * FROM article ORDER BY id DESC",
                mapper
        );
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select id, writer, title, contents from article where id = ?",
                mapper,
                id
        ));
    }


    private final RowMapper<Article> mapper = (rs, rowNum) -> {
        Article article = new Article(
                rs.getInt("id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents")
        );
        return article;
    };
}
