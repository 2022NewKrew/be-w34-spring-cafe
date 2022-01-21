package com.kakao.cafe.service.reply;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.dto.reply.ReplyRequest;
import com.kakao.cafe.dto.reply.ReplyResponse;
import com.kakao.cafe.dto.reply.ReplyUpdateRequest;
import com.kakao.cafe.repository.reply.ReplyRepository;
import com.kakao.cafe.util.exception.ReplyNotFoundException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository){
        this.replyRepository = replyRepository;
    }


    @Override
    public void addReply(ReplyRequest replyRequest) {
        Reply reply = Reply.builder()
                .articleId(replyRequest.getArticleId())
                .writer(replyRequest.getWriter())
                .contents(replyRequest.getContents())
                .createdAt(replyRequest.getCreatedAt())
                .deleted(false)
                .build();
        replyRepository.insert(reply);
    }

    @Override
    public List<ReplyResponse> findReplies(Long articleId) {
        return replyRepository.selectAll(articleId).stream()
                .map(ReplyResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ReplyResponse findReplyById(Long replyId) {
        return new ReplyResponse(replyRepository.selectByReplyId(replyId)
                .orElseThrow(() -> new ReplyNotFoundException("존재하지 않는 댓글입니다.")));
    }

    @Override
    public void modifyReply(ReplyUpdateRequest replyUpdateRequest, Boolean removal) {
        Reply reply = replyRepository.selectByReplyId(replyUpdateRequest.getId())
                .orElseThrow(()-> new ReplyNotFoundException("존재하지 않는 댓글입니다."));

        replyRepository.update(Reply.builder()
                .id(replyUpdateRequest.getId())
                .articleId(replyUpdateRequest.getArticleId())
                .writer(replyUpdateRequest.getWriter())
                .contents(reply.getContents())
                .createdAt(reply.getCreatedAt())
                .deleted(removal)
                .build());

    }
}
