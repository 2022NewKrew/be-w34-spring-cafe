package com.kakao.cafe.repository.comment;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Comment;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.article.Time;
import com.kakao.cafe.domain.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateCommentRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public Comment save(Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Number key = jdbcInsert.withTableName("COMMENT").usingGeneratedKeyColumns("comment_id")
                .executeAndReturnKey(inputParameters(comment));
        comment.setCommentId(key.longValue());
        return comment;
    }

    public Comment delete() {
        return null;
    }

    public Comment edit() {
        return null;
    }

    @Override
    public List<Comment> findAllOfArticle(Article article) {
        return jdbcTemplate.query("select * from COMMENT as c inner join member as m on c.author_id = m.member_id where article_id = ?", commentRowMapper(article), article.getArticleId());
    }

    private RowMapper<Comment> commentRowMapper(Article article) {
        return (rs, rowNum) -> new Comment(article,
                new Time(rs.getString("time")),
                new Text(rs.getString("text")),
                new Member(new UserId(rs.getString("user_id")), new Name(rs.getString("user_name")), new Password(rs.getString("password")), new Email(rs.getString("email")), rs.getLong("member_id")),
                rs.getLong("comment_id"));
    }

    private Map<String, Object> inputParameters(Comment comment) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("text", comment.getText().getText());
        parameters.put("article_id", comment.getArticle().getArticleId());
        parameters.put("time", comment.getTime().toString());
        parameters.put("author_id", comment.getAuthor().getMemberId());

        return parameters;
    }
}
