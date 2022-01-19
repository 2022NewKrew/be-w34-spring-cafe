package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ArticleDbRepository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleDbRepository.ArticleMapper mapper;

    public ArticleDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ArticleMapper();
    }

    @Override
    public void save(Article entity) {
        String sql = "insert into article (title, content, view_count, writer_email) values (?, ?, 0, ?)";
        jdbcTemplate.update(sql, entity.getTitle(), entity.getContent(), entity.getWriter().getEmail());
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        String sql = "" +
                "select a.*, u.* " +
                "from article as a " +
                "left join user as u " +
                "on a.writer_email = u.email " +
                "where article_id = ?";
        Article article = jdbcTemplate.queryForObject(sql, mapper, articleId);
        return Optional.ofNullable(article);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        String sql = "" +
                "select a.*, u.* " +
                "from article a " +
                "left join user u " +
                "on a.writer_email = u.email " +
                "ORDER BY a.reg_date DESC " +
                "limit ? offset ?";
        int totalRow = jdbcTemplate.queryForObject("select count(*) from article", (rs, rowNum) -> rs.getInt(1));
        int totalPage = (int) Math.ceil(totalRow / (double) pageable.getSize());
        int fromIndex = pageable.getPage() * pageable.getSize();
        if (fromIndex >= totalRow)
            return new Page<Article>(new ArrayList<>(), totalPage, totalRow, pageable);

        List<Article> articles = jdbcTemplate.query(sql, mapper, pageable.getSize(), fromIndex);
        return new Page<Article>(articles, totalPage, totalRow, pageable);
    }


    private static class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Article.builder()
                    .articleId(rs.getLong("article_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .viewCount(rs.getLong("view_count"))
                    .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                    .modDate(rs.getTimestamp("mod_date").toLocalDateTime())
                    .writer(User.builder()
                            .email(rs.getString("email"))
                            .username(rs.getString("username"))
                            .build())
                    .build();
        }
    }
}
