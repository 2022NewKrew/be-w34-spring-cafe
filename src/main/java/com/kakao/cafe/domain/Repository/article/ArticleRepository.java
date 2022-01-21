package com.kakao.cafe.domain.Repository.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.dto.article.PostArticleDto;
import com.kakao.cafe.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    // 새로운 게시물 저장
    public void save(PostArticleDto postArticleDto) {
        this.jdbcTemplate.update("INSERT INTO ARTICLES (userId, writer, title, contents) VALUES (?, ?, ?, ?)",
                postArticleDto.getUserId(), postArticleDto.getWriter(), postArticleDto.getTitle(), postArticleDto.getContents());
    }

    // 전체 게시물
    public List<Article> findAllNotDeleted() {
        return this.jdbcTemplate.query("SELECT * FROM ARTICLES WHERE deleted != 1", this.articleMapper);
    }

    // id로 게시물 찾기
    public Article findById(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM ARTICLES WHERE id = ?", this.articleMapper, id);
    }

    // 게시물 수정
    public void update(int id, String title, String contents) {
        this.jdbcTemplate.update("UPDATE ARTICLES SET title=?, contents=? WHERE id =?", title, contents, id);
    }

    // 게시물 삭제
    public void delete(int id) {
        this.jdbcTemplate.update("UPDATE ARTICLES SET deleted = 1 WHERE id = ?", id);
    }

}
