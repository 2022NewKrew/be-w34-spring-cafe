package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.common.Deleted;
import com.kakao.cafe.domain.reply.Comment;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReplyRowMapper implements RowMapper<Reply> {

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID replyId = UUID.fromString(rs.getString("replies_id"));
        Comment comment = new Comment(rs.getString("comment"));
        LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);
        Deleted deleted = Deleted.from(rs.getBoolean("deleted"));
        UUID articleId = UUID.fromString(rs.getString("articles_id"));
        UUID users_id = UUID.fromString(rs.getString("users_id"));
        UserName userName = new UserName(rs.getString("username"));
        Password password = new Password(rs.getString("password"));
        Name name = new Name(rs.getString("name"));
        Email email = new Email(rs.getString("email"));
        User writer = new User(users_id, userName, password, name, email);
        return new Reply(replyId, comment, writer, createdAt, deleted, articleId);
    }
}
