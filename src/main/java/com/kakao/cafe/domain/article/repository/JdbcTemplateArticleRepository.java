package com.kakao.cafe.domain.article.repository;

import com.kakao.cafe.domain.article.dto.ArticleAndCommentRawDataDto;
import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;
import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.sql.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    @Autowired
    public JdbcTemplateArticleRepository(DataSource dataSource, UserRepository userRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRepository = userRepository;
    }

    public ArticleRowDataDto save(ArticleRowDataDto articleRowDataDto) {
        final String sql = "insert into " + TableName.ARTICLE + " (`writer`, `title`, `contents`, `registerDateTime`) values (?,?,?,?)";

        jdbcTemplate.update(sql, articleRowDataDto.getWriterId(), articleRowDataDto.getTitle(), articleRowDataDto.getContents(), articleRowDataDto.getRegisterDateTime());

        return articleRowDataDto;
    }

    public Optional<ArticleRowDataDto> findById(Long articleId) {
        final String sql =
                "select *" +
                " from " + TableName.ARTICLE + " as a" +
                " inner join " + TableName.USER + " as u on a.writer = u.id" +
                " where a.id=?";
        List<ArticleRowDataDto> result = jdbcTemplate.query(sql, articleRowMapper(), articleId);
        return result.stream().findAny();
    }

    // Articles 과 Comments table, Users 을 조인하여 조회
    public List<ArticleAndCommentRawDataDto> findWithCommentById(Long articleId) {
        final String sql =
                "select                                     " +
                "a.id                                 as id," +
                "a.title                           as title," +
                "a.contents                     as contents," +
                "a.registerDateTime                        ," +
                "u.userId                   as authorUserId," +
                "c.id                          as commentId," +
                "c.contents                      as comment," +
                "u2.userId               as commenterUserId," +
                "u2.id                       as commenterId," +
                "c.registerDateTime           as commentTime" +
                " from " + TableName.ARTICLE + " as a" +
                " inner join " + TableName.USER + " as u on a.writer=u.id" +
                " left outer join " + TableName.COMMENT + " as c on a.id=c.articleId and c.isDeleted=false" +
                " left outer join " + TableName.USER + " as u2 on c.writerId=u2.id" +
                " where a.id=? and a.isDeleted=false";

        List<ArticleAndCommentRawDataDto> result = jdbcTemplate.query(sql, articleAndCommentRowMapper(), articleId);
        return result;
    }

    @Override
    public List<ArticleRowDataDto> findAll() {
        final String sql =
                "select *" +
                " from " + TableName.ARTICLE + " as a" +
                " inner join " + TableName.USER + " as u on a.writer = u.id";

        return jdbcTemplate.query(sql , articleRowMapper());
    }

    public int update(ArticleRowDataDto articleRowDataDto) {
        final String sql = "update " + TableName.ARTICLE + " set title=?, contents=? where id=? and isDeleted=false";

        return jdbcTemplate.update(sql, articleRowDataDto.getTitle(), articleRowDataDto.getContents(), articleRowDataDto.getId());
    }

    // Articles 와 Comments 에서 해당 article 관련 데이터 삭제
    @Override
    public boolean deleteById(Long articleId) {
        final String articleSQL = "update " + TableName.ARTICLE + " set isDeleted=true where id=?";
        final String commentSQL = " update " + TableName.COMMENT + " set isDeleted=true where articleId=?";
        if (jdbcTemplate.update(articleSQL, articleId) <= 0) throw new RuntimeException("삭제에 실패하였습니다.");
        if (jdbcTemplate.update(commentSQL, articleId) <= 0) throw new RuntimeException("삭제에 실패하였습니다.");
        return true;
    }

    private RowMapper<ArticleRowDataDto> articleRowMapper() {
        return (rs, rowNum) -> {
            ArticleRowDataDto articleRowDataDto = new ArticleRowDataDto();
            articleRowDataDto.setId(rs.getLong("id"));
            articleRowDataDto.setWriterId(rs.getLong("writer"));
            articleRowDataDto.setWriterUserId(rs.getString("userId"));
            articleRowDataDto.setTitle(rs.getString("title"));
            articleRowDataDto.setContents(rs.getString("contents"));
            articleRowDataDto.setRegisterDateTime(rs.getTimestamp("registerDateTime").toLocalDateTime());
            return articleRowDataDto;
        };
    }

    private RowMapper<ArticleAndCommentRawDataDto> articleAndCommentRowMapper() {
        return (rs, rowNum) -> {
            ArticleAndCommentRawDataDto articleAndCommentDto = new ArticleAndCommentRawDataDto();
            articleAndCommentDto.setId(rs.getLong("id"));
            articleAndCommentDto.setAuthorUserId(rs.getString("authorUserId"));
            articleAndCommentDto.setTitle(rs.getString("title"));
            articleAndCommentDto.setContents(rs.getString("contents"));
            articleAndCommentDto.setArticleRegisterDateTime(rs.getTimestamp("registerDateTime").toLocalDateTime());
            articleAndCommentDto.setCommentId(rs.getLong("commentId"));
            articleAndCommentDto.setCommenterUserId(rs.getString("commenterUserId"));
            articleAndCommentDto.setComment(rs.getString("comment"));
            articleAndCommentDto.setCommentTime(convertToLocalDateTime(rs.getTimestamp("commentTime")));
            return articleAndCommentDto;
        };
    }

    // null 체크를 위해
    private LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) { return null;}
        return timestamp.toLocalDateTime();
    }

    // 삭제를 요청한 User id 와 article id 로 삭제 가능 여부 체크 (다른 사람이 남긴 댓글이 존재하는지)
    public boolean checkCanDelete(Long articleId, Long userLongId) {
        final String sql =
                "select CASE" +
                " when authorCnt = 1 and commenterCnt = 0 then true" +
                " else false" +
                " END as result" +
                " from (select count(distinct a.writer) as authorCnt, count(distinct c.writerId) as commenterCnt" +
                      " from articles as a" +
                      " left outer join comments as c on a.id = c.articleId and c.isDeleted=false and c.writerId != ?" +
                      " where a.id = ? and a.writer = ?) as rs";

        return jdbcTemplate.queryForObject(sql, Boolean.class, userLongId, articleId, userLongId);
    }
}
