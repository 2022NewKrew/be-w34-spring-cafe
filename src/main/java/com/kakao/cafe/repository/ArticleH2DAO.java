package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ArticleH2DAO implements ArticleDAOInterface {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper = new ArticleMapper();

    @Autowired
    public ArticleH2DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        String sql = "INSERT INTO ARTICLE VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getId(),
                article.writeTimeToStr(),
                article.getWriter(),
                article.getTitle(),
                article.getContents());
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT ID, WRITETIME, WRITER, TITLE, CONTENTS FROM ARTICLE";
        return jdbcTemplate.query(sql, articleMapper);
    }


    @Override
    public Article findById(long id) {
        String sql = "SELECT ID, WRITETIME, WRITER, TITLE, CONTENTS FROM ARTICLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, articleMapper, id);
    }

    private static class ArticleMapper implements RowMapper<Article> {
        private DateTimeFormatter formatType = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Article(
                    rs.getLong("ID"),
                    LocalDateTime.parse(rs.getString("WRITETIME"), formatType),
                    rs.getString("WRITER"),
                    rs.getString("TITLE"),
                    rs.getString("CONTENTS")
            );
        }
    }
}
