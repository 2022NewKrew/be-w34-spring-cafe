package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Primary
public class ArticleH2Dao implements ArticleDAOInterface {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper = new ArticleMapper();
    private final Logger logger = LoggerFactory.getLogger(ArticleH2Dao.class);

    @Autowired
    public ArticleH2Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO ARTICLE(WRITETIME, WRITER, TITLE, CONTENTS) VALUES (?, ?, ?, ?)",
                    new String[]{"ID"}
            );
            pstmt.setString(1, article.writeTimeToStr());
            pstmt.setString(2, article.getWriter());
            pstmt.setString(3, article.getTitle());
            pstmt.setString(4, article.getContents());
            return pstmt;
        }, keyHolder);
        logger.info(String.valueOf(keyHolder.getKey()) + "번째 글 생성");
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
