package com.kakao.cafe.dao.reply;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.reply.ReplyFactory;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DisplayName("JdbcReplyStorage 테스트")
@JdbcTest
class JdbcReplyStorageTest {

    private static final int NUMBER_OF_USER = 2;
    private static final int NUMBER_OF_ARTICLE = 1;
    private static final int NUMBER_OF_REPLY = 1;
    private final ReplyDao replyDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcReplyStorageTest(JdbcTemplate jdbcTemplate) {
        this.replyDao = new JdbcReplyStorage(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    private void insertInitData() {
        String userSql = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        for (int i = 1; i <= NUMBER_OF_USER; i++) {
            jdbcTemplate.update(userSql, "userId" + i, "password" + i, "name" + i, "email" + i);
        }

        String articleSql = "INSERT INTO ARTICLE(TITLE, USER_ID, CONTENTS) VALUES (?, ?, ?)";
        for (int i = 1; i <= NUMBER_OF_ARTICLE; i++) {
            jdbcTemplate.update(articleSql, "title" + i, "userId" + i, "contents" + i);
        }

        String replySqlByUser1 = "INSERT INTO REPLY(ARTICLE_ID, USER_ID, COMMENT) VALUES (1, 'userId1', ?)";
        for (int i = 1; i <= NUMBER_OF_REPLY; i++) {
            jdbcTemplate.update(replySqlByUser1, "contents" + i);
        }

        String replySqlByUser2 = "INSERT INTO REPLY(ARTICLE_ID, USER_ID, COMMENT) VALUES (1, 'userId2', ?)";
        for (int i = 1; i <= NUMBER_OF_REPLY; i++) {
            jdbcTemplate.update(replySqlByUser2, "contents" + i);
        }
    }

    @AfterEach
    private void deleteInitData() {
        String preSql = "SET REFERENTIAL_INTEGRITY FALSE";
        String postSql = "SET REFERENTIAL_INTEGRITY TRUE";
        String truncateReplySql = "TRUNCATE TABLE REPLY";
        String truncateArticleSql = "TRUNCATE TABLE ARTICLE";
        String truncateUserSql = "TRUNCATE TABLE USER_DATA";
        String initArticleAutoIncrement = "ALTER TABLE ARTICLE ALTER COLUMN ID RESTART WITH 1";
        String initReplyAutoIncrement = "ALTER TABLE REPLY ALTER COLUMN ID RESTART WITH 1";
        jdbcTemplate.execute(preSql);
        jdbcTemplate.execute(truncateReplySql);
        jdbcTemplate.execute(truncateArticleSql);
        jdbcTemplate.execute(truncateUserSql);
        jdbcTemplate.execute(initReplyAutoIncrement);
        jdbcTemplate.execute(initArticleAutoIncrement);
        jdbcTemplate.execute(postSql);
    }

    @DisplayName("입력받은 reply에 맞게 값을 저장한다.")
    @Test
    void addReply() {
        //give
        int articleId = NUMBER_OF_ARTICLE;
        String userId = "userId1";
        ReplyCreateDto replyCreateDto = new ReplyCreateDto(articleId, userId, "comment");
        Reply newReply = ReplyFactory.getReply(replyCreateDto);
        int beforeLength = replyDao.getReplies(articleId).size();

        //when
        replyDao.addReply(newReply);
        int afterLength = replyDao.getReplies(articleId).size();

        //then
        assertThat(afterLength).isEqualTo(beforeLength + 1);
    }

    @DisplayName("입력받은 aritcleId를 가지는 Reply를 반환한다.")
    @Test
    void getReplies() {
        //give
        int articleId = NUMBER_OF_ARTICLE;

        //when
        int numberOfReply = replyDao.getReplies(articleId).size();

        //then
        assertThat(numberOfReply).isEqualTo(NUMBER_OF_REPLY * NUMBER_OF_USER);
    }

    @DisplayName("입력받은 replyId를 갖는 Reply를 제거한다.")
    @Test
    void deleteReply() {
        //give
        int replyId = NUMBER_OF_REPLY;
        int beforeLength = replyDao.getReplies(replyId).size();

        //when
        replyDao.deleteReply(replyId);
        int afterLength = replyDao.getReplies(replyId).size();

        //then
        assertThat(afterLength).isEqualTo(beforeLength - 1);
    }

    @DisplayName("입력받은 replyId를 갖는 Reply를 반환한다.")
    @Test
    void findReplyById() {
        //give
        ReplyCreateDto replyCreateDto = new ReplyCreateDto(NUMBER_OF_ARTICLE,
                "userId" + NUMBER_OF_USER, "newComment");
        Reply newReply = ReplyFactory.getReply(replyCreateDto);
        replyDao.addReply(newReply);
        int newReplyId = NUMBER_OF_USER * NUMBER_OF_REPLY + 1;

        //when
        Reply reply = replyDao.findReplyById(newReplyId).orElseGet(null);

        //then
        assertThat(reply.getComment().getValue()).isEqualTo("newComment");
    }
}
