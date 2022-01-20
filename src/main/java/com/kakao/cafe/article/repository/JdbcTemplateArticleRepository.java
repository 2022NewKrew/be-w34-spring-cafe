package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Primary
@Transactional
@Repository
@RequiredArgsConstructor
public class JdbcTemplateArticleRepository implements ArticleRepository {


    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {
        if (article.getId() != null) {
            update(article);
            return;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("insert into ARTICLES (USER_FK,WRITER,TITLE,CONTENTS,WRITING_TIME,COUNT_COMMENT)" +
                            "values (?,?,?,?,?,?)",
                    new String[]{"ID"});
            pstmt.setLong(1, article.getUserFk());
            pstmt.setString(2, article.getWriter());
            pstmt.setString(3, article.getTitle());
            pstmt.setString(4, article.getContents());
            pstmt.setString(5, article.getWritingTime());
            pstmt.setLong(6, article.getCountOfComment());
            return pstmt;
        }, keyHolder);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Article> findById(Long id) {
        return jdbcTemplate.query("select * from ARTICLES where ID = ?",
                articleRowMapper(), id).stream().findAny();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from ARTICLES",
                articleRowMapper());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from ARTICLES where ID = ?", id);
    }

    private void update(Article article) {
        jdbcTemplate.update("update ARTICLES set TITLE=?,CONTENTS=?,WRITING_TIME=? where ID = ?", article.getTitle(), article.getContents(), article.getWritingTime(), article.getId());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("ID"),
                rs.getLong("USER_FK"),
                rs.getString("WRITER"),
                rs.getString("TITLE"),
                rs.getString("CONTENTS"),
                rs.getString("WRITING_TIME"),
                rs.getLong("COUNT_COMMENT"));
    }
}
