package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class SpringJdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    @Override
    public void save(Article article) {
        jdbcTemplate.update("insert into ARTICLE(writer_id,title,contents) values ( ?,?,? )",
                article.getWriter().getUserId(), article.getTitle(), article.getContents());
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = jdbcTemplate.queryForObject("select * from ARTICLE a join USERS u on a.writer_id = u.user_id  where a.article_seq_id=?", articleMapper, id);
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from ARTICLE a join USERS u on a.writer_id = u.user_id", articleMapper);
    }
}
