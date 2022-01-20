package com.kakao.cafe.replies;

import com.kakao.cafe.CafeApplicationTests;
import com.kakao.cafe.articles.Article;
import com.kakao.cafe.articles.JdbcArticleRepository;
import com.kakao.cafe.users.JdbcUserRepository;
import com.kakao.cafe.users.User;
import com.kakao.cafe.users.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReplyServiceTest extends CafeApplicationTests {
    @Mock
    JdbcUserRepository userRepository;
    @Mock
    JdbcReplyRepository replyRepository;
    @Mock
    JdbcArticleRepository articleRepository;

    @InjectMocks
    ReplyService replyService;

    @Test
    void 댓글생성() {
        User givenUser = users.get(0);
        Article givenArticle = articles.get(0);
        Reply givenReply = replies.get(0);

        given(userRepository.findById(any())).willReturn(Optional.ofNullable(givenUser));
        given(articleRepository.findById(any())).willReturn(Optional.ofNullable(givenArticle));
        given(replyRepository.save(any())).willReturn(givenReply);

        ReplyCreationRequest request = new ReplyCreationRequest();
        request.setWriterId(givenUser.getId());
        request.setArticleId(givenArticle.getId());
        request.setContent(givenReply.getContent());

        UserDto me = UserDto.toDto(givenUser);

        ReplyDto actual = replyService.createReply(request, me);

        Assertions.assertThat(actual.getContent()).isEqualTo(givenReply.getContent());
        Assertions.assertThat(actual.getArticleId()).isEqualTo(givenReply.getArticleId());
        Assertions.assertThat(actual.getWriterId()).isEqualTo(givenReply.getWriterId());
        Assertions.assertThat(actual.isStatus()).isEqualTo(givenReply.isStatus());
    }

    @Test
    void getAllReplyByArticleIdAndStatus() {
        Article givenArticle = articles.get(0);
        List<Reply> givenReplies = List.of(
                replies.get(0),
                replies.get(1)
        );

        given(replyRepository.findAllByArticleIdAndStatus(any(),any())).willReturn(List.of(
                replies.get(0),
                replies.get(1)
        ));
        given(articleRepository.findById(any())).willReturn(Optional.ofNullable(givenArticle));

        List<ReplyDto> actual = replyService.getAllReplyByArticleIdAndStatus(givenArticle.getId(), true);

        Assertions.assertThat(actual).hasSize(givenReplies.size());
    }
}