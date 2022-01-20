package com.kakao.cafe.adapter.out.infra.persistence.reply;

import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_ARTICLE_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_CONTENTS;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_CREATED_AT;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_DELETED;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_USER_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository.COLUMN_WRITER;

import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ReplyMapper implements RowMapper<Reply> {

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Reply reply = new Reply.Builder().articleId(rs.getInt(COLUMN_ARTICLE_ID))
                                             .userId(rs.getString(COLUMN_USER_ID))
                                             .writer(rs.getString(COLUMN_WRITER))
                                             .contents(rs.getString(COLUMN_CONTENTS))
                                             .createdAt(rs.getString(COLUMN_CREATED_AT))
                                             .deleted(rs.getBoolean(COLUMN_DELETED))
                                             .build();
            reply.setId(rs.getInt(COLUMN_ID));
            return reply;
        } catch (IllegalWriterException e) {
            throw new SQLException("DB에 저장된 writer가 잘못되었습니다.");
        } catch (IllegalTitleException e) {
            throw new SQLException("DB에 저장된 title이 잘못되었습니다.");
        } catch (IllegalDateException e) {
            throw new SQLException("DB에 저장된 createdAt 값이 잘못되었습니다.");
        } catch (IllegalUserIdException e) {
            throw new SQLException("DB에 저장된 userId 값이 잘못되었습니다.");
        }
    }

}
