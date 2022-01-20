package com.kakao.cafe.domain.article.repository;

import com.kakao.cafe.domain.article.dto.ArticleAndCommentRawDataDto;
import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;
import com.kakao.cafe.domain.user.User;
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

    public Optional<ArticleRowDataDto> findById(Long id) {
        List<ArticleRowDataDto> result = jdbcTemplate.query("select * from " + TableName.ARTICLE +" where id=?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    public List<ArticleAndCommentRawDataDto> findWithCommentById(Long id) {
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

        List<ArticleAndCommentRawDataDto> result = jdbcTemplate.query(sql, articleAndCommentRowMapper(), id);
        return result;
    }

    public Optional<ArticleRowDataDto> findByWriter(String writer) {
        List<ArticleRowDataDto> result = jdbcTemplate.query("select * from " + TableName.ARTICLE +" where writer=? and isDeleted=false", articleRowMapper(), writer);
        return result.stream().findAny();
    }

    @Override
    public List<ArticleRowDataDto> findAll() {
        return jdbcTemplate.query("select * from " + TableName.ARTICLE + " where isDeleted=false", articleRowMapper());
    }

    public ArticleRowDataDto update(ArticleRowDataDto articleRowDataDto) {
        final String sql = "update " + TableName.ARTICLE + " set title=?, contents=? where id=? and isDeleted=false";

        jdbcTemplate.update(sql, articleRowDataDto.getTitle(), articleRowDataDto.getContents(), articleRowDataDto.getId());
        return articleRowDataDto;
    }

    @Override
    public boolean deleteById(Long id) {
        final String articleSQL = "update " + TableName.ARTICLE + " set isDeleted=true where id=?";
        final String commentSQL = " update " + TableName.COMMENT + " set isDeleted=true where articleId=?";
        if (jdbcTemplate.update(articleSQL, id) <= 0) throw new RuntimeException("삭제에 실패하였습니다.");
        if (jdbcTemplate.update(commentSQL, id) <= 0) throw new RuntimeException("삭제에 실패하였습니다.");
        return true;
    }

    private RowMapper<ArticleRowDataDto> articleRowMapper() {
        return (rs, rowNum) -> {
            ArticleRowDataDto articleRowDataDto = new ArticleRowDataDto();
            articleRowDataDto.setId(rs.getLong("id"));
            articleRowDataDto.setWriterId(rs.getLong("writer"));
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
            articleAndCommentDto.setCommentTime(converToLocalDateTime(rs.getTimestamp("commentTime")));
            return articleAndCommentDto;
        };
    }

    private LocalDateTime converToLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) { return null;}
        return timestamp.toLocalDateTime();
    }

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
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, Boolean.class, userLongId, articleId, userLongId);
    }
}
