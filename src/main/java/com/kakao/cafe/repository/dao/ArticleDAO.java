package com.kakao.cafe.repository.dao;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ARTICLES TABLE의 Id는 ARTICLES TABLE의 AUTO_INCREMENT 옵션으로 자동 생성.
    public void create(Article article) {
        String sql = "INSERT INTO ARTICLES (TITLE,CONTENT,CREATE_USER_ID,CREATE_DATE,VIEWS) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getCreateUserId(), article.getCreateDate(), article.getViews());
    }

    public Article findById(long id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> Article.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createUserId(rs.getString("create_user_id"))
                .createDate(rs.getString("create_date"))
                .views(rs.getInt("views"))
                .build();
    }

    public void update(Long id,String title,String content){
        String sql = "UPDATE ARTICLES " +
                "SET TITLE=?, CONTENT=?" +
                "WHERE ID = ?";
        jdbcTemplate.update(sql,title,content,id);
    }
}
