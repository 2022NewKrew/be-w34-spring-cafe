package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.ReplyMapper;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.article.SpringJdbcArticleRepository;
import com.kakao.cafe.repository.user.SpringJdbcUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Sql("classpath:script.sql")
class SpringJdbcReplyRepositoryTest {

    SpringJdbcUserRepository springJdbcUserRepository;
    SpringJdbcArticleRepository springJdbcArticleRepository;
    SpringJdbcReplyRepository springJdbcReplyRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        UserMapper userMapper = new UserMapper();
        springJdbcUserRepository = new SpringJdbcUserRepository(jdbcTemplate, userMapper);
        springJdbcArticleRepository = new SpringJdbcArticleRepository(jdbcTemplate, new ArticleMapper(userMapper));
        springJdbcReplyRepository = new SpringJdbcReplyRepository(jdbcTemplate, new ReplyMapper(userMapper));
    }

    @Test
    @DisplayName("댓글 게시글 아이디로 조회")
    void testOfFindAll() {
        User articleUser = User.builder()
                .userId("leaf")
                .name("김남현")
                .password("123")
                .email("leaf.hyeon@kakaocorp.com")
                .build();
        User replyUser = User.builder()
                .userId("leaf2")
                .name("김남현2")
                .password("123")
                .email("leaf2.hyeon@kakaocorp.com")
                .build();
        springJdbcUserRepository.save(articleUser);
        springJdbcUserRepository.save(replyUser);
        Article article = Article.builder()
                .writer(articleUser)
                .title("title")
                .contents("contents")
                .build();
        springJdbcArticleRepository.save(article);
        List<Article> articles = springJdbcArticleRepository.findAll();

        Reply reply1 = Reply.builder()
                .articleId(articles.get(0).getId())
                .writer(replyUser)
                .comment("aogoaofaso")
                .build();
        article.addReply(reply1);
        Reply reply2 = Reply.builder()
                .articleId(articles.get(0).getId())
                .writer(replyUser)
                .comment("aogoaofaso222")
                .build();
        article.addReply(reply2);
        springJdbcArticleRepository.save(article);
        springJdbcReplyRepository.save(reply1);
        springJdbcReplyRepository.save(reply2);

        List<Reply> all = springJdbcReplyRepository.findByArticleId(articles.get(0).getId());

        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}