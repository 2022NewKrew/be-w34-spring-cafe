package com.kakao.cafe.dao.article;

import com.kakao.cafe.dao.reply.ReplyDao;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.user.UserId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcArticleStorage implements ArticleDao {

    private final JdbcTemplate jdbcTemplate;
    private final ReplyDao replyDao;

    public JdbcArticleStorage(JdbcTemplate jdbcTemplate, ReplyDao replyDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.replyDao = replyDao;
    }

    @Override
    public List<Article> getArticles(int pageNumber, int articlesPerPage) {
        int startIndex = (pageNumber - 1) * articlesPerPage;
        int finishIndex = articlesPerPage * (pageNumber);

        List<Article> articles = getPartOfArticles(startIndex, finishIndex);
        setReplies(articles);

        return articles;
    }

    @Override
    public Article addArticle(Article article) {
        String query = ArticleSql.insert(article);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> connection.prepareStatement(query, new String[]{"id"}), keyHolder);

        return findArticleById(keyHolder.getKey().intValue()).orElseThrow(
                () -> new RuntimeException("Article 생성 후 반환시 발생한 예외"));
    }

    @Override
    public Optional<Article> findArticleById(int id) {
        return jdbcTemplate
                .query(ArticleSql.findArticleById(id), (rs, rowNum) -> toArticleWithReplies(rs))
                .stream()
                .findAny();
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject(ArticleSql.count(), Integer.class);
    }

    @Override
    public void deleteArticle(int id) {
        jdbcTemplate.update(ArticleSql.delete(id));
        updateReply(id, new ArrayList<>());
    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update(ArticleSql.update(article));
        updateReply(article.getId(), article.getReplies());
    }

    private List<Article> getPartOfArticles(int startIndex, int finishIndex) {
        List<Article> articles = getAllArticles();

        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    private List<Article> getAllArticles() {
        return jdbcTemplate.query(ArticleSql.getAllArticleNotDeleted(),
                (rs, rowNum) -> toArticle(rs));
    }

    private void setReplies(List<Article> articles) {
        for (Article article : articles) {
            setReply(article);
        }
    }

    private void setReply(Article article) {
        Logger logger = LoggerFactory.getLogger(JdbcArticleStorage.class);
        List<Reply> replies = replyDao.getReplies(article.getId());
        for (Reply reply : replies) {
            logger.info("reply id is {}", reply.getId());
        }
        article.setReplies(replies);
    }

    private Article toArticle(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt("ID"),
                new Title(resultSet.getString("TITLE")),
                new UserId(resultSet.getString("USER_ID")),
                new Contents(resultSet.getString("CONTENTS")),
                resultSet.getTimestamp("CREATE_DATE").toLocalDateTime(),
                resultSet.getBoolean("DELETED"),
                new ArrayList<>()
        );
    }

    private Article toArticleWithReplies(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt("ID"),
                new Title(resultSet.getString("TITLE")),
                new UserId(resultSet.getString("USER_ID")),
                new Contents(resultSet.getString("CONTENTS")),
                resultSet.getTimestamp("CREATE_DATE").toLocalDateTime(),
                resultSet.getBoolean("DELETED"),
                replyDao.getReplies(resultSet.getInt("ID"))
        );
    }

    private void updateReply(int articleId, List<Reply> newReplies) {
        List<Reply> oldReply = replyDao.getReplies(articleId);
        for (Reply reply : oldReply) {
            replyDao.deleteReply(reply.getId());
        }
        for (Reply reply : newReplies) {
            replyDao.addReply(reply);
        }
    }
}
