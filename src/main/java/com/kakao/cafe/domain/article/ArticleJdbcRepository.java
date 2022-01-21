package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.core.exception.NoSuchArticle;
import com.kakao.cafe.core.DBConst;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArticleJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(ArticleSaveForm article) {
        jdbcTemplate.update("insert into " + DBConst.ARTICLE_DB + " (authorId, author, title, content) values(?,?,?,?)"
                , article.getAuthorId()
                , article.getAuthor()
                , article.getTitle()
                , article.getContent());
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

    @Override
    public void decrementNumOfComment(Long articleId) {
        String sql = "update " + DBConst.ARTICLE_DB + " set numOfComment = numOfComment - 1 where id = ?";
        jdbcTemplate.update(sql, articleId);
    }

    @Override
    public boolean canDeleteAndCheckAuthority(Long id, Long userId) {
        String sql = "Select CASE \n" +
                "WHEN authorCnt = 1 and replyCnt = 0 THEN true\n" +
                "WHEN authorCnt = 1 and replyCnt = 1 THEN true\n" +
                "else false\n" +
                "END as result\n" +
                "from (SELECT count(distinct a.authorId) authorCnt, count(distinct r.replyerId) replyCnt\n" +
                "FROM article a left join reply r on a.id = r.articleId\n" +
                "WHERE a.authorId = ? and a.id = ?)";
        List<Boolean> result = jdbcTemplate.query(sql, booleanMapper(), userId, id);
        return result.get(0);
    }

    @Override
    public void updateAuthorName(Long id, String name) {
        String sql = "update " + DBConst.ARTICLE_DB + " set author = ? where authorId = ?";
        jdbcTemplate.update(sql, name, id);
    }

    @Override
    public List<Article> findByPage(int pageNum) {
        String sql = "select * from " + DBConst.ARTICLE_DB + " LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, articleRowMapper(), DBConst.NUM_OF_ARTICLE_ON_PAGE, (pageNum - 1) * DBConst.NUM_OF_ARTICLE_ON_PAGE + 1);
    }

    @Override
    public int getPageCnt() {
        List<Integer> query = jdbcTemplate.query("select count(*) as cnt from article", cntRowMapper());
        return query.stream().findAny().get();
    }

    private RowMapper<Integer> cntRowMapper() {
        return (rs, rowNum) -> rs.getInt("cnnt");
    }

    private RowMapper<Boolean> booleanMapper() {
        return (rs, rowNum) -> rs.getBoolean("result");
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
