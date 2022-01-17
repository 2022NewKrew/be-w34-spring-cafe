package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.domain.dto.article.ArticleContents;
import com.kakao.cafe.domain.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.dto.article.ArticleListShow;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.util.TimeStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Primary
public class ArticleJdbcRepository implements ArticleRepository {
    private static final String STORE_SQL =
            "INSERT INTO ARTICLES(WRITER, TITLE, CONTENT, CREATED_DATE) VALUES(?, ?, ?, ?)";
    private static final String RETRIEVE_SQL =
            "SELECT * FROM ARTICLES WHERE ARTICLE_ID=?";
    private static final String MODIFY_SQL =
            "UPDATE ARTICLES SET WRITER=?, TITLE=?, CONTENT=? WHERE ARTICLE_ID=?";
    private static final String DELETE_SQL =
            "DELETE FROM ARTICLES WHERE ARTICLE_ID=?";
    private static final String TO_LIST_SQL =
            "SELECT * FROM ARTICLES";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(ArticleCreateCommand acc) {
        jdbcTemplate.update(STORE_SQL,
                acc.getWriter(),
                acc.getTitle(),
                acc.getContents(),
                TimeStringParser.parseTimeToString(LocalDateTime.now()));
    }

    @Override
    public ArticleContents retrieve(Long id) {
        return jdbcTemplate.queryForObject(RETRIEVE_SQL, articleContentsRowMapper(), id);
    }

    @Override
    public void modify(Long id, Article article) {
        jdbcTemplate.update(MODIFY_SQL,
                article.getWriter(),
                article.getTitle(),
                article.getContents(),
                id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public List<ArticleListShow> toList() {
        return jdbcTemplate.query(TO_LIST_SQL, articleListShowRowMapper());
    }

    public RowMapper<ArticleListShow> articleListShowRowMapper() {
        return (rs, rowNum) -> new ArticleListShow(
                rs.getLong("ARTICLE_ID"),
                rs.getString("CREATED_DATE"),
                rs.getString("WRITER"),
                rs.getString("TITLE")
        );
    }

    public RowMapper<ArticleContents> articleContentsRowMapper() {
        return (rs, rowNum) -> new ArticleContents(
                rs.getString("CREATED_DATE"),
                rs.getString("WRITER"),
                rs.getString("TITLE"),
                rs.getString("CONTENT")
        );
    }
}
