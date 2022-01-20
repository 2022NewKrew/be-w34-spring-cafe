package com.kakao.cafe.article.repository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.entity.mapper.ArticleRowMapper;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private static final String SAVE_QUERY = "INSERT INTO article(writer, title, contents) VALUES (?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM article WHERE is_deleted = false";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM article WHERE id = ? AND is_deleted = false";
    private static final String UPDATE_QUERY = "UPDATE article SET title = ?, contents = ?, modify_time = CURRENT_TIME WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE article SET is_deleted = true, modify_time = CURRENT_TIME WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;

    @Override
    public void save(Article article) {
        jdbcTemplate.update(SAVE_QUERY, article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = jdbcTemplate.query(FIND_ALL_QUERY, articleRowMapper);

        return articleList;
    }

    @Override
    public Optional<Article> findById(int id) {
        List<Article> article = jdbcTemplate.query(FIND_BY_ID_QUERY, articleRowMapper, id);

        return article.stream().findFirst();
    }

    @Override
    public void update(int id, Article article) {
        jdbcTemplate.update(UPDATE_QUERY, article.getTitle(), article.getContents(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
