package com.kakao.cafe.dao;

import com.kakao.cafe.dao.mapper.ArticleRowMapper;
import com.kakao.cafe.vo.ArticleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;

    public void save(ArticleVo articleVo) {
        String writer = articleVo.getWriter();
        String title = articleVo.getTitle();
        String contents = articleVo.getContents();
        String date = articleVo.getDate();
        String userId = articleVo.getUserId();
        jdbcTemplate.update("INSERT INTO article (writer, title, contents, date, userId) VALUES (?, ?, ?, ?, ?)", writer, title, contents, date, userId);
    }

    public ArticleVo findByArticleId(int articleId) {
        List<ArticleVo> resultList = jdbcTemplate.query("SELECT articleId, writer, title, contents, date, userId FROM article WHERE articleId = ?", articleRowMapper, articleId);
        return resultList.stream()
                .findFirst()
                .orElse(null);
    }

    public List<ArticleVo> findAllByPage(int offset, int limit) {
        return jdbcTemplate.query("SELECT articleId, writer, title, contents, date, userId FROM article ORDER BY articleId DESC LIMIT ? OFFSET ?", articleRowMapper, limit,offset);
    }

    public void update(ArticleVo articleVo) {
        int articleId = articleVo.getArticleId();
        String title = articleVo.getTitle();
        String contents = articleVo.getContents();
        String date = articleVo.getDate();
        jdbcTemplate.update("UPDATE article SET TITLE = ?, CONTENTS = ?, DATE = ? WHERE articleId = ?",title,contents,date,articleId);
    }

    public void deleteByArticleId(int articleId) {
        jdbcTemplate.update("DELETE FROM article WHERE articleId = ?",articleId);
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM article",Integer.class);
    }
}
