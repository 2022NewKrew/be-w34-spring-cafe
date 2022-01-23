package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.reply.ReplyRepository;
import com.kakao.cafe.dto.reply.CreateReplyDto;
import com.kakao.cafe.dto.reply.ShowReplyDto;
import com.kakao.cafe.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void createReply(CreateReplyDto replyDto, Long postId, String userId) {
        Reply reply = Reply.builder()
                .comment(replyDto.getComment())
                .userId(userId)
                .postId(postId)
                .build();

        replyRepository.insert(reply);
    }

    public List<ShowReplyDto> findAllReply(Long postId) {
        return replyRepository.findAll(postId).stream()
                .map(ShowReplyDto::new)
                .collect(Collectors.toList());
    }

    public ShowReplyDto findOneReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).
                orElseThrow(() -> new NotFoundException("존재하지 않는 댓글입니다."));

        return new ShowReplyDto(reply);
    }

    public void deleteReply(Long replyId) {
        replyRepository.delete(replyId);
    }

}
