package com.kakao.cafe.module.repository;

import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Primary
@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addArticle(Article article) {
        String query = "INSERT INTO ARTICLE (author_id, title, contents) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setLong(1, article.getAuthorId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getContents());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }

    @Override
    public ArticleReadDto findArticleById(Long id) {
        String query = "SELECT ARTICLE.id, USERS.name, USERS.id, ARTICLE.title, ARTICLE.created, ARTICLE.contents, ARTICLE.comment_count" +
                " FROM ARTICLE" +
                " JOIN USERS" +
                " ON ARTICLE.author_id = USERS.id" +
                " WHERE ARTICLE.id = ?";
        return jdbcTemplate.query(query, mapRowArticle(), id).stream().findAny()
                .orElseThrow(() -> new NoSuchDataException("해당하는 사용자가 없습니다."));
    }

    @Override
    public List<ArticleListDto> findAllArticles() {
        String query = "SELECT ARTICLE.id, USERS.name, USERS.id, ARTICLE.title, ARTICLE.created, ARTICLE.comment_count" +
                " FROM ARTICLE" +
                " JOIN USERS" +
                " ON ARTICLE.author_id = USERS.id" +
                " WHERE ARTICLE.status = TRUE";
        return jdbcTemplate.query(query, mapRowArticles());
    }

    @Override
    public void updateArticleCommentCount(Long id, int value) {
        int commentCount = jdbcTemplate.query("SELECT comment_count FROM ARTICLE WHERE id = ?",
                        (rs, rowNum) -> rs.getInt("comment_count"), id)
                .stream().findAny()
                .orElseThrow(() -> new NoSuchDataException("해당하는 게시물이 없습니다."));

        jdbcTemplate.update("UPDATE ARTICLE SET comment_count = ? WHERE id = ?",
                commentCount + value, id);
    }

    @Override
    public void updateArticle(Long id, String title, String contents) {
        jdbcTemplate.update("UPDATE ARTICLE SET title = ?, contents = ? WHERE id = ?",
                title, contents, id);
    }

    @Override
    public void deleteArticle(Long id) {
        jdbcTemplate.update("UPDATE ARTICLE SET comment_count = 0, status = false WHERE id = ?", id);
    }

    private RowMapper<ArticleListDto> mapRowArticles() {
        return ((rs, rowNum) -> new ArticleListDto(
                rs.getLong("ARTICLE.id"),
                rs.getString("USERS.name"),
                rs.getLong("USERS.id"),
                rs.getString("ARTICLE.title"),
                rs.getTimestamp("ARTICLE.created").toLocalDateTime(),
                rs.getInt("ARTICLE.comment_count")
        ));
    }

    private RowMapper<ArticleReadDto> mapRowArticle() {
        return ((rs, rowNum) -> new ArticleReadDto(
                rs.getLong("ARTICLE.id"),
                rs.getString("USERS.name"),
                rs.getLong("USERS.id"),
                rs.getString("ARTICLE.title"),
                rs.getTimestamp("ARTICLE.created").toLocalDateTime(),
                rs.getString("ARTICLE.contents"),
                rs.getInt("ARTICLE.comment_count")
        ));
    }
}
