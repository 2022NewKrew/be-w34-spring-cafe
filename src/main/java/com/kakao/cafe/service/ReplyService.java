package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.exception.UnauthenticatedArticleAccessException;
import com.kakao.cafe.repository.ReplyRepository;
import java.util.List;
import java.util.UUID;
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

    public List<Reply> getReplyListOfArticle(UUID articleId) {
        return replyRepository.findAllByArticleId(articleId);
    }

    public void deleteReplyByIdAndAuthor(UUID replyId, User user) {
        Reply reply = replyRepository.findReplyById(replyId)
                .orElseThrow(() -> new NoSuchArticleException("해당 댓글을 찾을 수 없습니다."));
        if (!reply.isWriter(user)) {
            throw new UnauthenticatedArticleAccessException("다른 사람의 게시글을 수정, 삭제할 수 없습니다");
        }
        replyRepository.delete(reply);
    }
}
