package com.kakao.cafe.reply.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.domain.ReplyRepository;
import com.kakao.cafe.reply.service.dto.CreateReplyServiceRequest;
import com.kakao.cafe.reply.service.dto.OneReplyServiceResponse;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public List<OneReplyServiceResponse> findReplyByArticle(Long articleId) {
        List<Reply> replies = replyRepository.findAll(articleId);
        return ReplyServiceDTOMapper.convertReplyListToDTO(replies);
    }

    public Long createReply(CreateReplyServiceRequest req) {
        Reply reply = makeReply(req);
        return replyRepository.persist(reply);
    }

    private Reply makeReply(CreateReplyServiceRequest req) {
        return Reply.builder()
                    .articleId(req.getArticleId())
                    .authorStringId(req.getAuthorStringId())
                    .contents(req.getContents())
                    .authorId(req.getAuthorId())
                    .build();
    }
}
