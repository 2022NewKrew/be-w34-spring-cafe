package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ArticleJdbcRepository implements ArticleRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addArticle(ArticleCreateDTO articleCreateDTO) {

        jdbcTemplate.update("INSERT INTO articles(name,title,contents,date) VALUES (?,?,?,?)",
                articleCreateDTO.getContents(),
                articleCreateDTO.getTitle(),
                articleCreateDTO.getContents(),
                new Date()
        );

    }

    @Override
    public List<Article> getArticles() {
        return jdbcTemplate.query("select * from articles",
                (rs, rn) ->
                {Article article = new Article(rs.getString("name"), rs.getString("title"), rs.getString("contents"), rs.getDate("date"), rs.getLong("sequence"));
                    return article;});
    }
}
