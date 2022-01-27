package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_ARTICLE;

public class SpringJdbcMemoryArticle implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcMemoryArticle(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("articles").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", article.getAuthor());
        parameters.put("title", article.getTitle());
        parameters.put("content", article.getContent());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setArticleID(key.longValue());

        return article;
    }

    @Override
    public void update(Article article){
        jdbcTemplate.update("update articles set title=?, content=? where id=?",article.getTitle(), article.getContent(),article.getArticleID());
    }

    @Override
    public void delete(Long articleID) {
        jdbcTemplate.update("delete articles where id=?", articleID);
    }

    @Override
    public Article findByID(Long articleID) {
        List<Article> result =  jdbcTemplate.query("select * from articles where id = ?",articleRowMapper(), articleID);
        return result.stream().findAny().orElseThrow(() -> new CustomException(NOT_EXIST_ARTICLE));
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from articles", articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper(){
        return new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                String author = rs.getString("author");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Article article = Article.add(author, new SampleArticleForm(title, content));
                article.setArticleID(rs.getLong("id"));
                return article;
            }
        };
    }
}
