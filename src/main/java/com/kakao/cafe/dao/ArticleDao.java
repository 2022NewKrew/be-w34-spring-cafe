package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDao {

    private JdbcTemplate jdbcTemplate;

    public ArticleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addArticle(Article article) {
        jdbcTemplate.update("insert into article(writer, title, contents) values(?,?,?)",
                article.getWriter(), article.getTitle(), article.getContents());
    }

    public List<Article> getArticles() {
        return jdbcTemplate.query("select * from article",
                (rs, rowNum) -> new Article(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents")
                )
        );
    }

    public Article getArticle(int index) {
        return jdbcTemplate.queryForObject("select * from article where id = ?",
                (rs, rowNum) -> new Article(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents")
                ),
                index
        );
    }

}
