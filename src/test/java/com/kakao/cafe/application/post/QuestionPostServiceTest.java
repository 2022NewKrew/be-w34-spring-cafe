package com.kakao.cafe.application.post;

import com.kakao.cafe.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.application.user.UserAccountService;
import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.post.QuestionPostRepository;
import com.kakao.cafe.domain.user.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class QuestionPostServiceTest {

    @Mock
    UserAccountService userAccountService;

    @Mock
    QuestionPostRepository questionPostRepository;

    @Test
    void getPostDetailTest() {
        //given
        Long postId = 1L;
        String author = "peach";
        String title = "질문";
        String content = "질문내용";
        int viewCount = 0;
        LocalDateTime date = LocalDateTime.now();
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long accountId = 1L;

        QuestionPostDetailResult expected = new QuestionPostDetailResult(
                postId,
                title,
                content,
                formatDate,
                viewCount,
                author
        );

        given(userAccountService.getUserInfo(accountId)).willReturn(
                UserAccount.builder()
                        .username(author)
                        .build());

        given(questionPostRepository.findById(postId)).willReturn(Optional.of(
                QuestionPost.builder()
                        .questionPostId(postId)
                        .title(title)
                        .content(content)
                        .createdAt(date)
                        .viewCount(viewCount)
                        .userAccountId(accountId)
                        .build()));

        //when
        QuestionPostService questionPostService = new QuestionPostService(questionPostRepository, userAccountService);
        QuestionPostDetailResult postDetail = questionPostService.getPostDetail(postId);

        //then
        Assertions.assertThat(postDetail.getQuestionPostId()).isEqualTo(expected.getQuestionPostId());
        Assertions.assertThat(postDetail.getAuthor()).isEqualTo(expected.getAuthor());
    }
}
