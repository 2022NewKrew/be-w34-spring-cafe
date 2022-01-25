package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.exceptions.UnauthenticatedPostAccessException;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.response.ReplyDetailResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    public ReplyService(ReplyRepository replyRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    public void registerReply(Reply reply) {
        replyRepository.save(reply);
    }

    public List<ReplyDetailResponse> getReplyListOfPost(int postId) {
        return replyRepository.findAllByPostId(postId).stream()
                .map(reply -> ReplyDetailResponse.of(reply, userRepository.findById(reply.getUserId())))
                .collect(Collectors.toList());
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
