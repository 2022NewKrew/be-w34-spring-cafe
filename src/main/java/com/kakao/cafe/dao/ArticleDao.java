package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addArticle(Article article) {
        jdbcTemplate.update("insert into articles(writer, title, contents) values(?,?,?)",
                article.getWriter(), article.getTitle(), article.getContents());
    }

    public List<Article> getArticles() {
        return jdbcTemplate.query("select * from articles",
                (rs, rowNum) -> new Article(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents")
                )
        );
    }

    public Article getArticle(int index) {
        return jdbcTemplate.queryForObject("select * from articles where id = ?",
                (rs, rowNum) -> new Article(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents")
                ),
                index
        );
    }

}
