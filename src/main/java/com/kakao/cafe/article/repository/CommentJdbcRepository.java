package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.Comment;
import com.kakao.cafe.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentJdbcRepository implements CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment){
        String sql = "INSERT INTO COMMENTS (articleId, author, contents, uploadTime) VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comment.getArticleId(), comment.getAuthor(),
                comment.getContents(), DateUtils.getCurrentDate());
    }

//    public RowMapper<Comment> commentRowMapper() {
//        return (rs, rowNum) -> new Comment(
//                rs.getLong("id"),
//                rs.getString("author"),
//                rs.getString("title"),
//                rs.getString("contents"),
//                rs.getString("uploadTime")
//        );
//    }
}
