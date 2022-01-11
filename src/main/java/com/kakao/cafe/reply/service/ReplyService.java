package com.kakao.cafe.reply.service;

import com.kakao.cafe.reply.repository.CreateReplyRequestDTO;
import com.kakao.cafe.reply.repository.ReplyRepository;

public class ReplyService {

    ReplyRepository replyRepository = ReplyRepository.getRepository();

    public void createReply(Long authorId, Long articleId, String contents) {
        replyRepository.persist(new CreateReplyRequestDTO(articleId, authorId, contents));
    }

    public FindAllReplyByArticleResponseDTO getAllReplyByArticleResponse(Long articleId) {
        return new FindAllReplyByArticleResponseDTO(replyRepository.findByArticle(articleId));
    }

}
