package com.kakao.cafe.dao;

import com.kakao.cafe.dao.mapper.ArticleRowMapper;
import com.kakao.cafe.vo.ArticleVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ArticleVo articleVo) {
        String writer = articleVo.getWriter();
        String title = articleVo.getTitle();
        String contents = articleVo.getContents();
        String date = articleVo.getDate();
        jdbcTemplate.update("INSERT INTO ARTICLE (writer, title, contents, date) VALUES (?, ?, ?, ?)",writer,title,contents,date);
    }

    public ArticleVo findById(int id) {
        List<ArticleVo> resultList = jdbcTemplate.query("SELECT id, writer, title, contents, date FROM ARTICLE WHERE id = ?", new ArticleRowMapper(), id);
        return resultList.stream()
                .findFirst()
                .orElse(null);
    }

    public List<ArticleVo> findAll() {
        return jdbcTemplate.query("SELECT id, writer, title, contents, date FROM article", new ArticleRowMapper());
    }
}
