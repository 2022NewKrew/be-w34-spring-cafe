package com.kakao.cafe.repository;

import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbc.JdbcSQLNonTransientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ReplyDto;

@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleRowMapper;
    private final ResultSetExtractor<ArticleDto> articleDtoResultExtractor;
    private final RowMapper<Reply> replyRowMapper;

    @Autowired
    public ArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        articleDtoResultExtractor = getArticleDtoExtractor();
        articleRowMapper = getArticleMapper();
        replyRowMapper = getReplyMapper();
    }

    public void save(Article article) {
        String query = "INSERT INTO Articles (title, content, writer) VALUES (?, ?, ?)";

        jdbcTemplate.update(query, article.getTitle(), article.getContent(), article.getWriter());
    }

    public void saveReply(Reply reply) {
        String query = "INSERT INTO Reply (articleIndex, writer, content) VALUES (?, ?, ?)";

        jdbcTemplate.update(query, reply.getArticleIndex(), reply.getWriter(), reply.getContent());
    }

    public void update(Article article) {
        String query = "UPDATE Articles SET title = ?, content = ? WHERE articleIndex = ?";

        jdbcTemplate.update(query, article.getTitle(), article.getContent(), article.getArticleIndex());
    }

    public void delete(Integer articleIndex) {
        String query = "DELETE Articles WHERE articleIndex = ?";

        jdbcTemplate.update(query, articleIndex);
    }

    public List<Article> findAll() {
        String query = "SELECT * FROM Articles";

        return (List<Article>) jdbcTemplate.query(query, articleRowMapper);
    }

    public ArticleDto findByIndex(Integer articleIndex) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT Articles.title, Articles.content, Articles.writer, Articles.articleIndex, Reply.writer, Reply.content, Reply.replyIndex ");
        query.append("FROM Articles ");
        query.append("LEFT OUTER JOIN Reply ");
        query.append("Using (articleIndex) ");
        query.append("WHERE Articles.articleIndex = ?");

        return jdbcTemplate.query(query.toString(), articleDtoResultExtractor, articleIndex);
    }

    public Reply findReplyByIndex(Integer replyIndex) {
        String query = "SELECT articleIndex, writer, content, replyIndex FROM Reply WHERE replyIndex = ?";

        return jdbcTemplate.queryForObject(query, replyRowMapper, replyIndex);
    }

    public void deleteReply(Integer replyIndex) {
        String query = "DELETE Reply WHERE replyIndex = ?";

        jdbcTemplate.update(query, replyIndex);
    }
    private RowMapper<Article> getArticleMapper() {
        return (resultSet, rowNumber) -> new Article(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
    }

    private RowMapper<Reply> getReplyMapper() {
        return (resultSet, rowNumber) -> new Reply(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4));
    }

    private ResultSetExtractor<ArticleDto> getArticleDtoExtractor() {
        return rs -> {
            rs.next();
            ArticleDto articleDTO = new ArticleDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );
            articleDTO.getReplyDTOList().add(
                    new ReplyDto(
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getInt(7)
                    )
            );

            while (rs.next()) {
                articleDTO.getReplyDTOList().add(
                        new ReplyDto(
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7)
                        )
                );
            }

            return articleDTO;
        };
    }
}

