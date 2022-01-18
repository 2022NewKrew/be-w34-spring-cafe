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
        jdbcTemplate.update("INSERT INTO ARTICLE (writer, title, contents, date, userId) VALUES (?, ?, ?, ?, ?)", writer, title, contents, date, userId);
    }

    public ArticleVo findById(int id) {
        List<ArticleVo> resultList = jdbcTemplate.query("SELECT id, writer, title, contents, date, userId FROM ARTICLE WHERE id = ?", articleRowMapper, id);
        return resultList.stream()
                .findFirst()
                .orElse(null);
    }

    public List<ArticleVo> findAll() {
        return jdbcTemplate.query("SELECT id, writer, title, contents, date, userId FROM article", articleRowMapper);
    }

    public void update(ArticleVo articleVo) {
        int id = articleVo.getId();
        String title = articleVo.getTitle();
        String contents = articleVo.getContents();
        String date = articleVo.getDate();
        System.out.println(id);
        System.out.println(title);
        jdbcTemplate.update("UPDATE ARTICLE SET TITLE = ?, CONTENTS = ?, DATE = ? WHERE ID = ?",title,contents,date,id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM ARTICLE WHERE ID = ?",id);
    }
}
