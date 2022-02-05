package com.kakao.cafe.dao;

import com.kakao.cafe.dto.ArticleDbDto;
import com.kakao.cafe.dto.ArticleSaveDto;
import com.kakao.cafe.dto.ArticleUpdateDto;
import com.kakao.cafe.util.mapper.ArticleRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(ArticleSaveDto articleSaveDto) {
        String sql = "INSERT INTO ARTICLES(WRITER_ID, WRITE_TIME, TITLE, CONTENTS) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, articleSaveDto.getWriterId(), articleSaveDto.getWriteTime(), articleSaveDto.getTitle(), articleSaveDto.getContents());
    }

    public ArticleDbDto findByArticleId(Long articleId) throws EmptyResultDataAccessException {
        String sql = "SELECT A.ARTICLE_ID, U.NAME, A.WRITE_TIME, A.TITLE, A.CONTENTS FROM ARTICLES A INNER JOIN USERS U ON A.WRITER_ID = U.USER_ID WHERE ARTICLE_ID = ? AND DELETED = FALSE";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), articleId);
    }

    public ArticleDbDto findByArticleIdAndLoginId(Long articleId, String userId) throws EmptyResultDataAccessException {
        String sql = "SELECT A.ARTICLE_ID, U.NAME, A.WRITE_TIME, A.TITLE, A.CONTENTS FROM ARTICLES A INNER JOIN USERS U ON A.WRITER_ID = U.USER_ID WHERE ARTICLE_ID = ? AND USER_ID = ? AND DELETED = FALSE";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), articleId, userId);
    }

    public List<ArticleDbDto> findAll() {
        String sql = "SELECT A.ARTICLE_ID, U.NAME, A.WRITE_TIME, A.TITLE, A.CONTENTS FROM ARTICLES A INNER JOIN USERS U ON A.WRITER_ID = U.USER_ID AND DELETED = FALSE";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    public int update(ArticleUpdateDto article) {
        String sql = "UPDATE ARTICLES SET TITLE = ?, CONTENTS = ? WHERE ARTICLE_ID = ? AND WRITER_ID = ? AND DELETED = FALSE";
        return jdbcTemplate.update(sql, article.getTitle(), article.getContents(), article.getArticleId(), article.getWriterId());
    }

    public int delete(Long articleId, String userId) {
        String sql = "UPDATE ARTICLES SET DELETED = TRUE WHERE ARTICLE_ID = ? AND WRITER_ID = ?";
        return jdbcTemplate.update(sql, articleId, userId);
    }
}
