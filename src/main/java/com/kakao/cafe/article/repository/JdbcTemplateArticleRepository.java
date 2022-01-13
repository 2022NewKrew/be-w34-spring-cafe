package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.factory.ArticleFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final ArticleFactory articleFactory;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(QuestionDTO articleDTO) {

        Article article = articleFactory.of(articleDTO);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("insert into ARTICLES (WRITER,TITLE,CONTENTS,WRITING_TIME,COUNT_COMMENT)" +
                            "values (?,?,?,?,?)",
                    new String[]{"ID"});
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContents());
            pstmt.setString(4, article.getWritingTime());
            pstmt.setLong(5, article.getCountOfComment());
            return pstmt;
        }, keyHolder);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jdbcTemplate.query("select * from ARTICLES where ID = ?",
                articleRowMapper(), id).stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from ARTICLES",
                articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("ID"),
                rs.getString("WRITER"),
                rs.getString("TITLE"),
                rs.getString("CONTENTS"),
                rs.getString("WRITING_TIME"),
                rs.getLong("COUNT_COMMENT"));
    }
}
