package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArticleRepositoryJdbcTemplate implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositoryJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Article> selectAll() {
        return jdbcTemplate.query(
                "SELECT a.key, a.title, a.content, a.postTime, u.key, u.id, u.pw, u.name, u.email " +
                        "FROM article as a left join user as u " +
                        "on a.authorKey = u.key " +
                        "ORDER BY a.postTime DESC", articleRowMapper());
    }

    @Override
    public Optional<Article> selectByKey(Long key) {
        List<Article> result = jdbcTemplate.query(
                "SELECT a.key, a.title, a.content, a.postTime, u.key, u.id, u.pw, u.name, u.email " +
                        "from article as a left join user as u " +
                        "on a.authorKey = u.key " +
                        "where a.key = ?", articleRowMapper(), key);
        return result.stream().findAny();
    }

    @Override
    public Long insert(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("key");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorKey", article.getAuthor().getKey());
        parameters.put("title", article.getTitle());
        parameters.put("content", article.getContent());
        parameters.put("postTime", article.getPostTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key.longValue();
    }

    @Override
    public void update(Long key, Article article) {
        jdbcTemplate.update("UPDATE article SET title = ?, content = ? WHERE `key` = ?",
                article.getTitle(), article.getContent(), key);
    }

    @Override
    public void delete(Long key) {
        jdbcTemplate.update("DELETE FROM article WHERE `key` = ?", key);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = Article.builder()
                    .key(rs.getLong("a.key"))
                    .author(User.builder().key(rs.getLong("u.key"))
                            .id(rs.getString("u.id"))
                            .pw(rs.getString("u.pw"))
                            .name(rs.getString("u.name"))
                            .email(rs.getString("u.email"))
                            .build())
                    .title(rs.getString("a.title"))
                    .content(rs.getString("a.content"))
                    .postTime(rs.getTimestamp("a.postTime").toLocalDateTime())
                    .build();
            return article;
        };
    }
}
