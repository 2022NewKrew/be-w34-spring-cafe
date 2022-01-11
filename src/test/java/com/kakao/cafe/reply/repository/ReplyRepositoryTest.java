package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("댓글 저장과 조회 확인")
    void testReplySaveAndFind() throws Exception {
        // given

        Long id1 = replyRepository.persist(new CreateReplyRequestDTO(20L, 403L, "새로운 댓글"));
        // when
        Reply findReply = replyRepository.find(id1);
        // then
        assertThat(findReply.getArticleId()).isEqualTo(20L);
        assertThat(findReply.getAuthorId()).isEqualTo(403L);
        assertThat(findReply.getContents()).isEqualTo("새로운 댓글");
    }

    @Test
    @DisplayName("댓글 저장 후 게시글 별로 조회 확인")
    void testReplyFindByArticle() throws Exception {
        // given
        for (int i = 0; i < 10; i++) {
            replyRepository.persist(new CreateReplyRequestDTO(27L, 403L, "새로운 댓글 " + i));
        }
        for (int i = 0; i < 5; i++) {
            replyRepository.persist(new CreateReplyRequestDTO(55L, 1L, "예전 댓글 " + i));
        }

        // when
        ArrayList<Reply> findReplys1 = replyRepository.findByArticle(27L);
        ArrayList<Reply> findReplys2 = replyRepository.findByArticle(55L);

        // then
        assertThat(findReplys1.size()).isEqualTo(10);
        assertThat(findReplys2.size()).isEqualTo(5);
        assertThat(findReplys1.get(0).getContents()).contains("새로운 댓글 0");
        assertThat(findReplys2.get(3).getContents()).contains("예전 댓글 3");
    }
}
