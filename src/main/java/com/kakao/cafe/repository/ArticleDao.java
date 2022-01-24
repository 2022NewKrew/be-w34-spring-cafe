package com.kakao.cafe.repository;

import com.kakao.cafe.repository.constants.ArticleDBConstants;
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

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Article article) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName(ArticleDBConstants.TABLE_NAME)
                .usingColumns(ArticleDBConstants.COLUMN_WRITER, ArticleDBConstants.COLUMN_TITLE, ArticleDBConstants.COLUMN_CONTENTS)
                .usingGeneratedKeyColumns(ArticleDBConstants.COLUMN_ID);

        param.put(ArticleDBConstants.COLUMN_WRITER, article.getWriter());
        param.put(ArticleDBConstants.COLUMN_TITLE, article.getTitle());
        param.put(ArticleDBConstants.COLUMN_CONTENTS, article.getContents());

        int key = simpleJdbcInsert.executeAndReturnKey(param).intValue();

        if (key < 1)
            throw new SQLException("Article insertion fail.");

        return key;
    }

    public void update(Article article) {
        String sql = String.format("update %s set %s = ?, %s = ? where %s = ?",
                ArticleDBConstants.TABLE_NAME,
                ArticleDBConstants.COLUMN_TITLE,
                ArticleDBConstants.COLUMN_CONTENTS,
                ArticleDBConstants.COLUMN_ID);

        jdbcTemplate.update(sql, article.getTitle(), article.getContents(), article.getId());
    }

    public void delete(int id) {
        String sql = String.format("delete from %s where %s = ?",
                ArticleDBConstants.TABLE_NAME,
                ArticleDBConstants.COLUMN_ID);

        jdbcTemplate.update(sql, id);
    }

    public List<Article> findAll() {
        String sql = String.format("select * from %s", ArticleDBConstants.TABLE_NAME);

        return jdbcTemplate.query(
                sql,
                new ArticleMapper()
        );
    }

    public Article findById(int id) throws NoSuchElementException {
        Article article;
        String sql = String.format("select * from %s where %s = ?", ArticleDBConstants.TABLE_NAME, ArticleDBConstants.COLUMN_ID);

        try {
            article = jdbcTemplate.queryForObject(
                    sql,
                    new ArticleMapper(),
                    id
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException(String.format("id (%s) 를 갖는 article이 없음", id));
        }

        return article;
    }

    public static class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int count) throws SQLException {

            return new Article(
                    rs.getInt(ArticleDBConstants.COLUMN_ID),
                    rs.getString(ArticleDBConstants.COLUMN_WRITER),
                    rs.getString(ArticleDBConstants.COLUMN_TITLE),
                    rs.getString(ArticleDBConstants.COLUMN_CONTENTS)
            );
        };
    }
}
