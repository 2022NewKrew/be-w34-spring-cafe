package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.mapper.ArticleMapper;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into ARTICLE_TABLE (WRITER, TITLE, CONTENTS, TIME) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getWriter());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);
        return (Long)keyHolder.getKey();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from ARTICLE_TABLE", ArticleMapper.INSTANCE);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jdbcTemplate.
                query("select * from ARTICLE_TABLE where id = ?", ArticleMapper.INSTANCE, id).
                stream().findAny();

    }

    @Override
    public void updateArticle(Long id, Article updateArticle) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("UPDATE ARTICLE_TABLE SET TITLE = ?, CONTENTS = ? where id = ?");
            ps.setString(1, updateArticle.getTitle());
            ps.setString(2, updateArticle.getContents());
            ps.setString(3, String.valueOf(id));
            return ps;
        });
    }

    @Override
    public void deleteArticle(Long id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ARTICLE_TABLE where id = ?");
            ps.setString(1, String.valueOf(id));
            return ps;
        });
    }


}
