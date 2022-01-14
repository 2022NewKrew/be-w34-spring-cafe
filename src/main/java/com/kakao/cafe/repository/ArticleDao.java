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
public class ArticleDao implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "ARTICLE";
    private final String COLUMN_ID = "id";
    private final String COLUMN_WRITER = "writer";
    private final String COLUMN_TITLE = "title";
    private final String COLUMN_CONTENTS = "contents";


    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Article article) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName(TABLE_NAME)
                .usingColumns(COLUMN_WRITER, COLUMN_TITLE, COLUMN_CONTENTS)
                .usingGeneratedKeyColumns(COLUMN_ID);

        param.put(COLUMN_WRITER, article.getWriter());
        param.put(COLUMN_TITLE, article.getTitle());
        param.put(COLUMN_CONTENTS, article.getContents());

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
                    rs.getInt(COLUMN_ID),
                    rs.getString(COLUMN_WRITER),
                    rs.getString(COLUMN_TITLE),
                    rs.getString(COLUMN_CONTENTS)
            );
        };
    }
}
