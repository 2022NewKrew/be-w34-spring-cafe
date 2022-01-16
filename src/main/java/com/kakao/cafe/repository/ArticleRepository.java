package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void save(Article article) {
        String query = "INSERT INTO Articles (title, content, articleIndex)"
                       + String.format("VALUES ('%s', '%s', %d)", article.getTitle(), article.getContent(), article.getArticleIndex());
        jdbcTemplate.execute(query);
    }

    public List<Article> findAll() {
        String query ="SELECT * FROM Articles";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        for(Map<String, Object> articleInfo : result) {
            articles.add(new Article((String) articleInfo.get("title"), (String) articleInfo.get("content")));
        }

        return articles;
    }

    public Article findByIndex(Integer articleIndex) {
        String query = String.format("SELECT title, content, articleIndex FROM Articles WHERE articleIndex = '%s'", articleIndex);
        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return new Article((String) result.get("title"), (String) result.get("content"));
    }
}
