package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.exceptions.UnauthenticatedPostAccessException;
import com.kakao.cafe.repository.ReplyRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void registerReply(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> getReplyListOfPost(int postId) {
        return replyRepository.findAllByPostId(postId);
    }

    public void deleteReplyByIdAndAuthor(int replyId, int userId) {
        Reply reply = replyRepository.findReplyById(replyId);
        if (!reply.isUser(userId)) {
            throw new UnauthenticatedPostAccessException("다른 사람의 게시글을 수정, 삭제할 수 없습니다");
        }
        replyRepository.delete(replyId);
    }

    public void deleteReplyByPostId(int postId) {
        replyRepository.deleteAllByPostId(postId);
    }
}
