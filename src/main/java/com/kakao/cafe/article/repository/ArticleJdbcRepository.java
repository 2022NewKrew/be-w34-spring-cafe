package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ArticleJdbcRepository implements ArticleRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article){
        String sql = "INSERT INTO ARTICLE (author, title, contents, uploadTime) VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getAuthor(),article.getTitle(),
                article.getContents(), article.getUploadTime());
    }

    @Override
    public Long getNumberOfArticles(){
        String sql = "SELECT COUNT(*) FROM ARTICLE";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<Article> findAll(){
        String sql = "SELECT * FROM ARTICLE WHERE deleted=false";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    @Override
    public Optional<Article> findOneById(Long id){
        String sql = "SELECT * FROM ARTICLE WHERE id=?";
        return jdbcTemplate.query(sql, articleRowMapper(), id).stream().findAny();
    }

    @Override
    public void updateOne(Article article){
        String sql = "UPDATE ARTICLE SET author=?, title=?, contents=?, uploadTime=? WHERE id=?";
        jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(),
                article.getContents(), article.getUploadTime(), article.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "UPDATE ARTICLE SET deleted=true WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("uploadTime"),
                rs.getBoolean("deleted")
        );
    }
}
