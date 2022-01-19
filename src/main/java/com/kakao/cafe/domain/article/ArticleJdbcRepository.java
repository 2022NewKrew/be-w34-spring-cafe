package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.core.exception.NoSuchArticle;
import com.kakao.cafe.core.DBConst;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private static AtomicLong id = new AtomicLong(0L);
    private final JdbcTemplate jdbcTemplate;

    public ArticleJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(ArticleSaveForm article) {
        long articleId = id.incrementAndGet();
        jdbcTemplate.update("insert into " + DBConst.ARTICLE_DB + " values(?,?,?,?,?,?,?,?)"
                , articleId
                , article.getAuthorId()
                , article.getAuthor()
                , article.getTitle()
                , article.getContent()
                , LocalDateTime.now()
                , 0
                , 'N');
        return articleId;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from " + DBConst.ARTICLE_DB + " where isDeleted = 'N'", articleRowMapper());
    }

    @Override
    public Article findById(Long postId) {
        List<Article> query = jdbcTemplate.query("select * from " + DBConst.ARTICLE_DB + " where id=?", articleRowMapper(), postId);
        return query.stream().findAny().orElseThrow(() -> new NoSuchArticle("없는 게시글입니다."));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("update " + DBConst.ARTICLE_DB + " set isDeleted = 'Y' where id = ?", id);
    }

    @Override
    public boolean checkAuthor(Long id, Long userId) {
        List<Integer> query = jdbcTemplate.query("select id from " + DBConst.ARTICLE_DB + " where id = ? and authorId = ?", isExistMapper(), id, userId);
        return !query.isEmpty();
    }

    @Override
    public void update(Long id, ArticleUpdateForm updateForm) {
        jdbcTemplate.update("update " + DBConst.ARTICLE_DB + " set title = ?, content = ? where id = ?", updateForm.getTitle(), updateForm.getContent(), id);
    }

    @Override
    public void incrementNumOfComment(Long articleId) {
        String sql = "update " + DBConst.ARTICLE_DB + " set numOfComment = numOfComment + 1 where id = ?";
        jdbcTemplate.update(sql, articleId);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setArticleId(rs.getLong("id"));
            article.setAuthorId(rs.getLong("authorId"));
            article.setAuthor(rs.getString("author"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            article.setNumOfComment(rs.getLong("numOfComment"));

            return article;
        };
    }

    private RowMapper<Integer> isExistMapper() {
        return (rs, rowNum) -> rs.getInt("id");
    }
}
