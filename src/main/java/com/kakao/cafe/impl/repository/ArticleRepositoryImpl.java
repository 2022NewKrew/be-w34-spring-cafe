package com.kakao.cafe.impl.repository;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository("articleRepository")
public class ArticleRepositoryImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public long insertArticle(ArticleDTO article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into  Article (WRITERID, TITLE, CONTENTS) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, article.getWriterId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Long getArticleCount() {
        return jdbcTemplate.queryForObject("select count(id) from Article where isDelete = false", Long.class);
    }

    @Override
    public List<ArticleDTO> getAllArticle(Long page) {
        return jdbcTemplate.query("select A.id, WRITERID, USERID as writer, title, contents, views, date_format(A.time,'%Y-%m-%d %H:%i') time from User U join Article A on U.ID = A.WRITERID and isDelete = FALSE order by A.id desc limit ?,15",
                getRowMapper(), (page - 1) * Constants.ARTICLE_PER_PAGE
        );

    }

    @Override
    public ArticleDTO getArticleById(long id) {
        return jdbcTemplate
                .queryForObject("select A.id, WRITERID, USERID as writer, title, contents,views,  date_format(A.time,'%Y-%m-%d %H:%i') time from User U join Article A on U.ID = A.WRITERID where A.ID = ? and isDelete = FALSE",
                        getRowMapper(), id);
    }

    @Override
    public int increaseViews(long articleId) {
        return jdbcTemplate.update("update Article set views = views+1 where id = ?",
                articleId);
    }

    @Override
    public int updateArticle(long id, ArticleDTO article) {
        return jdbcTemplate.update("update Article set title = ?, contents = ? where id = ?", article.getTitle(), article.getContents(), id);
    }

    @Override
    public int deleteArticle(long id) {
        return jdbcTemplate.update("update Article set isDelete = TRUE where id = ?", id);
    }

    RowMapper<ArticleDTO> getRowMapper() {
        return (rs, rowNum) -> new ArticleDTO(
                rs.getLong("id"),
                rs.getLong("writerId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getLong("views"),
                rs.getString("time")
        );
    }
}
