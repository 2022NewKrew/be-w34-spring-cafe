package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.dto.reply.ReplyContents;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    public ReplyService(ReplyRepository replyRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    public void createReply(ReplyCreateCommand rcc) {
        replyRepository.store(rcc);
    }

    public void deleteReply(long replyId) {
        replyRepository.delete(replyId);
    }

    public ReplyContents getReply(long replyId) {
        Reply reply = replyRepository.retrieve(replyId)
                .orElseThrow(() -> new NoSuchElementException("Reply not found"));
        return new ReplyContents(reply.getReplyId(),
                userRepository.search(reply.getWriterId()).getName(),
                reply.getWriterId(),
                reply.getContents(),
                reply.getTime()
        );
    }

    public List<ReplyContents> getAllReplies(long articleId) {
        return replyRepository.toList(articleId).stream()
                .map(reply -> new ReplyContents(reply.getReplyId(),
                        userRepository.search(reply.getWriterId()).getName(),
                        reply.getWriterId(),
                        reply.getContents(),
                        reply.getTime()))
                .collect(Collectors.toUnmodifiableList());
    }
}
