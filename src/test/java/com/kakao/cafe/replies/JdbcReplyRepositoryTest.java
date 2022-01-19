package com.kakao.cafe.replies;

import com.kakao.cafe.CafeApplicationTests;
import com.kakao.cafe.articles.Article;
import com.kakao.cafe.articles.JdbcArticleRepository;
import com.kakao.cafe.users.JdbcUserRepository;
import com.kakao.cafe.users.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcReplyRepositoryTest extends CafeApplicationTests {
    @Autowired
    JdbcUserRepository jdbcUserRepository;
    @Autowired
    JdbcArticleRepository jdbcArticleRepository;
    @Autowired
    JdbcReplyRepository jdbcReplyRepository;

    @BeforeEach
    void setUp() {
        for (User user : users) {
            jdbcUserRepository.save(user);
        }

        for (Article article : articles) {
            jdbcArticleRepository.save(article);
        }
    }

    @Test
    void 저장및찾기() {
        Reply given = replies.get(0);

        jdbcReplyRepository.save(given);

        Reply actual = jdbcReplyRepository.findById(given.getId()).get();

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 한글에속한삭제안된댓글들() {
        jdbcReplyRepository.save(replies.get(0));
        jdbcReplyRepository.save(replies.get(2));

        List<Reply> actual = jdbcReplyRepository.findAllByArticleIdAndStatus(replies.get(0).getArticleId(), true);

        Assertions.assertThat(actual).hasSize(1);
    }

    @Test
    void delete() {
        Reply given = replies.get(0);
        jdbcReplyRepository.save(given);

        jdbcReplyRepository.delete(given.getId());

        Reply actual = jdbcReplyRepository.findById(given.getId()).get();

        Assertions.assertThat(actual.isStatus()).isFalse();
    }
}