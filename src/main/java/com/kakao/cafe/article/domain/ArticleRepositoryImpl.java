package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    @Override
    public void save(Article article) {
        final String sql = "insert into articles(title, body, author_id) values(?, ?, ?)";

        jdbcTemplate.update(sql, article.getTitle(), article.getBody(), article.getAuthorId());
    }

    @Override
    public Optional<SingleArticle> findSingleArticle(Long id) {
        final String sql = "select a.article_id, "
            + "a.title, "
            + "a.body, "
            + "a.created_at, "
            + "a.author_id, "
            + "a.view_count, "
            + "u.nickname as author_nickname "
            + "from articles as a inner join users u "
            + "on a.author_id = u.user_id "
            + "where a.article_id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> SingleArticle.builder()
                    .articleId(rs.getLong("article_id"))
                    .title(rs.getString("title"))
                    .body(rs.getString("body"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .viewCount(rs.getInt("view_count"))
                    .authorId(rs.getLong("author_id"))
                    .authorName(rs.getString("author_nickname"))
                    .build(),
                id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Article> findById(Long id) {
        final String sql = "select * from articles where article_id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, articleMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MultipleArticle> findAll() {
        final String sql = "select a.article_id, "
            + "a.title, "
            + "a.created_at, "
            + "a.author_id, "
            + "a.view_count, "
            + "u.nickname as author_nickname "
            + "from articles as a inner join users u "
            + "on a.author_id = u.user_id "
            + "order by a.created_at desc";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> MultipleArticle.builder()
                .articleId(rs.getLong("article_id"))
                .title(rs.getString("title"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .viewCount(rs.getInt("view_count"))
                .authorId(rs.getLong("author_id"))
                .authorName(rs.getString("author_nickname"))
                .build());
    }

    @Override
    public boolean increaseViewCount(Long id) {
        final String sql = "update articles set view_count = view_count + 1 where article_id = ?";

        try {
            return jdbcTemplate.update(sql, id) > 0;
        } catch (EmptyResultDataAccessException e) {
            throw new ArticleNotFoundException();
        }
    }

    @Override
    public void update(Article article) {
        final String sql = "update articles set title = ?, body = ? where article_id = ?";

        jdbcTemplate.update(sql, article.getTitle(), article.getBody(), article.getId());
    }

    @Override
    public void delete(Article article) {
        final String sql = "delete from articles where article_id = ?";

        jdbcTemplate.update(sql, article.getId());
    }
}
