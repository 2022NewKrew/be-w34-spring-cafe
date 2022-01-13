package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *  게시글 DB 관련 CRUD 처리
 */
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Article article) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName("ARTICLE")
                .usingColumns("writer", "title", "contents")
                .usingGeneratedKeyColumns("id");

        param.put("writer", article.getWriter());
        param.put("title", article.getTitle());
        param.put("contents", article.getContents());

        int key = simpleJdbcInsert.executeAndReturnKey(param).intValue();

        if (key < 1)
            throw new SQLException("Article insertion fail.");
    }

    public List<Article> findAll() {
        return jdbcTemplate.query(
                "select * from ARTICLE",
                new ArticleMapper()
        );
    }

    public Article findById(int id) throws NoSuchElementException {
        Article article;

        try {
            article = jdbcTemplate.queryForObject(
                    "select * from ARTICLE where id = ?",
                    new ArticleMapper(),
                    id
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException("해당 Id를 갖는 article이 없음");
        }

        return article;
    }

    public class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int count) throws SQLException {

            return new Article(
                    rs.getInt("id"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents")
            );
        };
    }
}
