package com.kakao.cafe.adapter.out.infra.persistence.reply;

import com.kakao.cafe.domain.article.Reply;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcReplyRepository implements ReplyRepository {

    final static String COLUMN_ID = "id";
    final static String COLUMN_ARTICLE_ID = "articleId";
    final static String COLUMN_USER_ID = "userId";
    final static String COLUMN_WRITER = "writer";
    final static String COLUMN_CONTENTS = "contents";
    final static String COLUMN_CREATED_AT = "createdAt";
    final static String COLUMN_DELETED = "deleted";
    private final static String REPLY_TABLE = "REPLY";
    private final static String QUERY_SELECT_ALL = "select * from " + REPLY_TABLE;

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reply> replyMapper;

    public JdbcReplyRepository(DataSource dataSource, RowMapper<Reply> replyMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.replyMapper = replyMapper;
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
        parameters.put(COLUMN_DELETED, reply.isDeleted());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<Reply> getAllReplyListByArticleId(int articleId) {
        String sql = QUERY_SELECT_ALL + " where " + COLUMN_ARTICLE_ID + "=? AND " + COLUMN_DELETED + "=false";
        return jdbcTemplate.query(sql, replyMapper, articleId);
    }

    @Override
    public void deleteById(int id) {
        String sql = "update " + REPLY_TABLE + " set " + COLUMN_DELETED + "=true" + " where " + COLUMN_ID + "=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllRepliesIn(int articleId) {
        String sql = "update " + REPLY_TABLE + " set " + COLUMN_DELETED + "=true" + " where " + COLUMN_ARTICLE_ID +
                     "=?";
        jdbcTemplate.update(sql, articleId);
    }
}
