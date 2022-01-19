package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
    @Resource(name = "replyRepository")
    ReplyRepository articleRepository;

    @Override
    public long insertReply(ReplyDTO reply) {
        return articleRepository.insertReply(reply);
    }

    @Override
    public List<ReplyDTO> getArticleReplies(long articleId, long userId) {
        return articleRepository.getArticleReplies(articleId, userId);
    }

    @Override
    public ReplyDTO getReplyById(long userId, long replyId) {
        return articleRepository.getReplyById(userId, replyId);
    }

    @Override
    public int deleteReply(long replyId) {
        return articleRepository.deleteReply(replyId);
    }

    @Override
    public int deleteAllReplies(long id) {
        return articleRepository.deleteAllReplies(id);
    }

    @Override
    public int getOtherUserRepliesCount(long articleId, long userId) {
        return articleRepository.getOtherUserRepliesCount(articleId, userId);
    }


}
