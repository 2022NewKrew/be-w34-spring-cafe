package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.dto.reply.ReplyContents;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) { this.replyRepository = replyRepository; }

    public void createReply(ReplyCreateCommand rcc) {
        replyRepository.store(rcc);
    }

    public void deleteReply(long replyId) {
        replyRepository.delete(replyId);
    }

    public ReplyContents getReply(long replyId) {
        Reply target = replyRepository.retrieve(replyId)
                .orElseThrow(() -> new NoSuchElementException("Reply not found"));
        return new ReplyContents(target);
    }

    public List<ReplyContents> getAllReplies(long articleId) {
        return replyRepository.toList(articleId).stream()
                .map(ReplyContents::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
