package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.repository.CreateReplyRequestDTO;
import com.kakao.cafe.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long createReply(Long authorId, Long articleId, String contents) {
        return replyRepository.persist(new CreateReplyRequestDTO(articleId, authorId, contents));
    }

    public FindAllReplyByArticleResponseDTO getAllReplyByArticleResponse(Long articleId) {
        return new FindAllReplyByArticleResponseDTO(replyRepository.findByArticle(articleId));
    }

}
