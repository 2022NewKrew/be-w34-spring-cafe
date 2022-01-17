package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.article.Time;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MemberRepository memberRepository;

    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Number key = jdbcInsert.withTableName("ARTICLE").usingGeneratedKeyColumns("article_id")
                .executeAndReturnKey(new MapSqlParameterSource(inputParameters(article)));
        article.setArticleId(key.longValue());
        return article;
    }

    @Override
    @Transactional(readOnly = true)
    public Article findArticle(Long articleId) {
        List<Article> result = jdbcTemplate.query("select * from article where article_id = ?", articleRowMapper(), articleId);
        Optional<Article> article = result.stream().findAny();
        if (article.isEmpty())
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_ARTICLE);
        log.info("output text : {}", article.get().getText().toString());
        return article.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAllArticle() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Article deleteArticle(Long articleId) {
        if (!isArticleExist(articleId))
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_ARTICLE);
        List<Article> result = jdbcTemplate.query("delete table article where article_id = ?", articleRowMapper(), articleId);
        return result.get(0);
    }

    @Override
    public void deleteAllArticle() {
        jdbcTemplate.query("delete table article", articleRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isArticleExist(Long articleId) {
        List<Article> result = jdbcTemplate.query("select * from article where article_id = ?", articleRowMapper(), articleId);
        return result.stream().findAny().isPresent();
    }

    private RowMapper<Article> articleRowMapper() {
        // n + 1 쿼리의 문제 발생
        return (rs, rowNum) -> new Article(
                new Title(rs.getString("title")),
                new Text(rs.getString("text")),
                memberRepository.findOne(rs.getLong("author_id")),
                new Time(rs.getString("time")),
                rs.getLong("article_id"));
    }

    private Map<String, Object> inputParameters(Article article) {

        Map<String, Object> parameters = new HashMap<>();

        log.info("input text : {}", article.getText().getText());
        parameters.put("title", article.getTitle().getTitle());
        parameters.put("text", article.getText().getText());
        parameters.put("time", article.getTime().toString());
        parameters.put("author_id", article.getAuthor().getMemberId());

        return parameters;
    }
}
