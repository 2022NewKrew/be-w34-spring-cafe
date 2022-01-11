package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.repository.ReplyRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.repository.UserRepository;
import com.kakao.cafe.user.service.FindAllUserResponseDTO;
import com.kakao.cafe.user.service.GetProfileResponseDTO;
import com.kakao.cafe.user.service.GetSignUpResultResponseDTO;
import com.kakao.cafe.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReplyServiceTest {

    ReplyRepository replyRepository = ReplyRepository.getRepository();
    ReplyService replyService = new ReplyService();

    @BeforeEach
    void init() {
        replyRepository.clear();
    }

    @Test
    @DisplayName("댓글 생성 및 조회 확인")
    void testReplySaveAndFind() throws Exception {
        // given
        replyService.createReply(20L, 40L, "새로운 댓글1");
        replyService.createReply(22L, 40L, "새로운 댓글2");
        replyService.createReply(24L, 60L, "새로운 댓글3");
        // when
        FindAllReplyByArticleResponseDTO dto = replyService.getAllReplyByArticleResponse(40L);
        // then
        assertThat(dto.allReplyByArticleDTO.size()).isEqualTo(2);
        assertThat(dto.allReplyByArticleDTO.get(0).contents).contains("새로운 댓글1");
        assertThat(dto.allReplyByArticleDTO.get(1).userId).isEqualTo(22L);
        assertThat(dto.allReplyByArticleDTO.get(1).contents).contains("새로운 댓글2");
    }


}
