package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private static Long idNumber = 0L;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(ArticleCreateRequestDto requestDto) {
        String sql = "INSERT INTO articles (writer, title, contents, deleted, created_at, modified_at) VALUES (?, ?, ?, false, now(), now())";
        jdbcTemplate.update(sql, requestDto.getWriter(), requestDto.getTitle(), requestDto.getContents());
    }

    @Override
    public List<Article> findNotDeleted() {
        String sql = "SELECT * FROM articles WHERE deleted = FALSE";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    @Override
    public Article findById(Long id) {
        String sql = "SELECT * FROM articles where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("해당하는 게시글이 존재하지 않습니다.");
        }
    }

    @Override
    public void shiftIsDeleted(Long id) {
        String sql = "UPDATE articles SET deleted = NOT deleted WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("게시글 삭제에 실패했습니다.");
        }
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(rs.getLong("id"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"));
    }
}
