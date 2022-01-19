package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.article.ArticleModifyCommand;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.util.TimeStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ArticleJdbcRepository implements ArticleRepository {
    private static final String STORE_SQL =
            "INSERT INTO ARTICLES(WRITER, WRITER_ID, TITLE, CONTENT, CREATED_DATE) VALUES(?, ?, ?, ?, ?)";
    private static final String RETRIEVE_SQL =
            "SELECT * FROM ARTICLES WHERE ARTICLE_ID=? AND DELETED=FALSE";
    private static final String MODIFY_SQL =
            "UPDATE ARTICLES SET TITLE=?, CONTENT=? WHERE ARTICLE_ID=?";
    private static final String DELETE_SQL =
            "UPDATE ARTICLES SET DELETED=TRUE WHERE ARTICLE_ID=?";
    private static final String TO_LIST_SQL =
            "SELECT * FROM ARTICLES WHERE DELETED=FALSE";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(ArticleCreateCommand acc) {
        jdbcTemplate.update(STORE_SQL,
                acc.getWriter(),
                acc.getWriterId(),
                acc.getTitle(),
                acc.getContents(),
                TimeStringParser.parseTimeToString(LocalDateTime.now()));
    }

    @Override
    public Optional<Article> retrieve(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(RETRIEVE_SQL, articleRowMapper(), id));
    }

    @Override
    public void modify(Long id, ArticleModifyCommand amc) {
        jdbcTemplate.update(MODIFY_SQL,
                amc.getTitle(),
                amc.getContents(),
                id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public List<Article> toList() {
        return jdbcTemplate.query(TO_LIST_SQL, articleRowMapper());
    }

    public RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("ARTICLE_ID"),
                rs.getString("WRITER"),
                rs.getString("WRITER_ID"),
                rs.getString("TITLE"),
                rs.getString("CONTENT"),
                TimeStringParser.parseStringToTime(rs.getString("CREATED_DATE"))
        );
    }
}
