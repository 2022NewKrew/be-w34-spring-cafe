package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyService replyService;

    @Test
    @DisplayName("댓글 생성 및 조회 확인")
    void testReplySaveAndFind() throws Exception {
        // given
        Long id1 = replyService.createReply(20L, 40L, "새로운 댓글1");
        Long id2 = replyService.createReply(22L, 40L, "새로운 댓글2");
        Long id3 = replyService.createReply(24L, 60L, "새로운 댓글3");
        // when
        AllReplyByArticleResponseDTO dto = replyService.getAllReplyByArticleResponse(40L);
        // then
        assertThat(dto.allReplyByArticleDTO.size()).isEqualTo(2);
        assertThat(dto.allReplyByArticleDTO.get(0).contents).contains("새로운 댓글1");
        assertThat(dto.allReplyByArticleDTO.get(1).userId).isEqualTo(22L);
        assertThat(dto.allReplyByArticleDTO.get(1).contents).contains("새로운 댓글2");
    }


}
