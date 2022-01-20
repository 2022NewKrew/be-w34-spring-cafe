package com.kakao.cafe.reply.service;

import java.util.List;
import java.util.Optional;

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

    public void deleteReply(Long replyId) {
        replyRepository.deleteReply(replyId);
    }

    public void validateAuthor(Long replyId, Long id) {
        Optional<Reply> op = replyRepository.find(replyId);
        Reply reply = op.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if (reply.getAuthorId() != id) {
            throw new IllegalArgumentException("작성자에게만 삭제 권한이 있습니다.");
        }
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
