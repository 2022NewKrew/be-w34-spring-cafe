package com.kakao.cafe.model.DAO;

import com.kakao.cafe.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Article> getArticles(){
        return jdbcTemplate.query("SELECT FROM BOARD", new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                Article article = new Article();

                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("article_title"));
                article.setContents(rs.getString("article_content"));
                article.setWriter(rs.getString("article_writer"));

                return article;
            }
        });
    }
}
