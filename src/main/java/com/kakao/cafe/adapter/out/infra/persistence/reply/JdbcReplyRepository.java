package com.kakao.cafe.adapter.out.infra.persistence.reply;

import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcReplyRepository implements ReplyRepository {

    private final static String REPLY_TABLE = "REPLY";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_ARTICLE_ID = "articleId";
    private final static String COLUMN_USER_ID = "userId";
    private final static String COLUMN_WRITER = "writer";
    private final static String COLUMN_CONTENTS = "contents";
    private final static String COLUMN_CREATED_AT = "createdAt";
    private final static String SELECT_ALL = "select * from " + REPLY_TABLE;
    private final static String DELETE_BY_ID = "delete from " + REPLY_TABLE + " where " + COLUMN_ID + "=?";

    private final JdbcTemplate jdbcTemplate;

    public JdbcReplyRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Reply reply) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(REPLY_TABLE).usingGeneratedKeyColumns(COLUMN_ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_ARTICLE_ID, reply.getArticleId());
        parameters.put(COLUMN_USER_ID, reply.getUserId());
        parameters.put(COLUMN_WRITER, reply.getWriter());
        parameters.put(COLUMN_CONTENTS, reply.getContents());
        parameters.put(COLUMN_CREATED_AT, reply.getCreatedAt());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<Reply> getAllReplyList() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> new ReplyMapper().mapRow(rs, rowNum));
    }

    private static final class ReplyMapper implements RowMapper<Reply> {

        @Override
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Reply reply = new Reply.Builder().articleId(rs.getInt(COLUMN_ARTICLE_ID))
                                                 .userId(rs.getString(COLUMN_USER_ID))
                                                 .writer(rs.getString(COLUMN_WRITER))
                                                 .contents(rs.getString(COLUMN_CONTENTS))
                                                 .createdAt(rs.getString(COLUMN_CREATED_AT))
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
}
