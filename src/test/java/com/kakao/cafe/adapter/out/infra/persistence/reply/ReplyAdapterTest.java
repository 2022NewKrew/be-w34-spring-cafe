package com.kakao.cafe.adapter.out.infra.persistence.reply;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReplyAdapterTest {

    @Mock
    ReplyRepository replyRepository;

    @InjectMocks
    ReplyAdapter replyAdapter;

    @DisplayName("게시글 정상 등록")
    @Test
    void postNormalReply() {
        // given
        WriteReplyRequest writeReplyRequest = new WriteReplyRequest.Builder().articleId(5)
                                                                             .userId("kakao")
                                                                             .writer("champ")
                                                                             .contents("Hello Kakao!")
                                                                             .build();

        // then
        assertThatNoException().isThrownBy(() -> replyAdapter.registerReply(writeReplyRequest));
    }

    @DisplayName("userId 누락 게시글 등록")
    @Test
    void postNullUserIdArticle() {
        // given
        WriteReplyRequest writeReplyRequest = new WriteReplyRequest.Builder().articleId(5)
                                                                             .userId("")
                                                                             .writer("champ")
                                                                             .contents("Hello Kakao!")
                                                                             .build();

        // then
        assertThatExceptionOfType(IllegalUserIdException.class)
            .isThrownBy(() -> replyAdapter.registerReply(writeReplyRequest));
    }

    @DisplayName("이름 누락 게시글 등록")
    @Test
    void postNullNameReply() {
        // given
        WriteReplyRequest writeReplyRequest = new WriteReplyRequest.Builder().articleId(5)
                                                                             .userId("kakao")
                                                                             .writer("")
                                                                             .contents("Hello Kakao!")
                                                                             .build();

        // then
        assertThatExceptionOfType(IllegalWriterException.class)
            .isThrownBy(() -> replyAdapter.registerReply(writeReplyRequest));
    }
}
