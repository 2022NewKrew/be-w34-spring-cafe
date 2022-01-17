package com.kakao.cafe.application.post;

import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.user.application.UserAccountService;
import com.kakao.cafe.post.application.QuestionPostService;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.domain.QuestionPostRepository;
import com.kakao.cafe.user.domain.UserAccount;
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
        UserAccount userAccount = UserAccount.builder()
                .userAccountId(1L)
                .username("baek")
                .build();

        QuestionPostDetailResult expected = new QuestionPostDetailResult(
                postId,
                title,
                content,
                formatDate,
                viewCount,
                author
        );

        given(questionPostRepository.findById(postId)).willReturn(Optional.of(
                QuestionPost.builder()
                        .questionPostId(postId)
                        .title(title)
                        .content(content)
                        .createdAt(date)
                        .viewCount(viewCount)
                        .userAccount(userAccount)
                        .build()));

        //when
        QuestionPostService questionPostService = new QuestionPostService(questionPostRepository, userAccountService);
        QuestionPostDetailResult postDetail = questionPostService.getPostDetail(postId);

        //then
        Assertions.assertThat(postDetail.getQuestionPostId()).isEqualTo(expected.getQuestionPostId());
        Assertions.assertThat(postDetail.getAuthor()).isEqualTo(expected.getAuthor());
    }
}
