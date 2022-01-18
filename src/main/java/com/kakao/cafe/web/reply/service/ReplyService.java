package com.kakao.cafe.web.reply.service;

import com.kakao.cafe.web.reply.domain.Reply;
import com.kakao.cafe.web.reply.dto.ReplyCreateDTO;
import com.kakao.cafe.web.reply.dto.ReplyUpdateDTO;
import com.kakao.cafe.web.reply.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReplyService {
    private final ReplyRepository memoryReplyRepository;

    public ReplyService(ReplyRepository memoryReplyRepository) {
        this.memoryReplyRepository = memoryReplyRepository;
    }

    public Reply writeReply(ReplyCreateDTO replyCreateDTO) {
        return memoryReplyRepository.createReply(replyCreateDTO);
    }

    public List<Reply> getReplyList() {
        return memoryReplyRepository.getReplyList();
    }

    public List<Reply> getReplyListByArticleId(long articleId) {
        return memoryReplyRepository.getReplyListByArticleId(articleId);
    }

    public List<Reply> getReplyListByWriter(String writer) {
        return memoryReplyRepository.getReplyListByWriter(writer);
    }

    public Reply getReplyById(long id) {
        Optional<Reply> reply = memoryReplyRepository.getReplyById(id);
        return reply.orElseThrow(NoSuchElementException::new);
    }

    public Reply updateReply(ReplyUpdateDTO replyUpdateDTO) {
        return memoryReplyRepository.updateReply(replyUpdateDTO);
    }

    public void deleteReplyById(long id) {
        memoryReplyRepository.deleteReplyById(id);
    }
}
