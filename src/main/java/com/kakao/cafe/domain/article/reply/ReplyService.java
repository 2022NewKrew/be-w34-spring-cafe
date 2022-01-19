package com.kakao.cafe.domain.article.reply;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.dto.ReplyRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Transactional
    public Long createReply(Reply reply, String currentUserId) {
        reply.checkAuthor(currentUserId);
        return replyRepository.save(reply);
    }

    @Transactional
    public Long deleteReply(Article article, Reply reply, String currentUserId) {
        article.checkIncludeReply(reply);
        reply.delete(currentUserId);
        return replyRepository.save(reply);
    }

    @Transactional(readOnly = true)
    public Reply findReplyByArticleAndId(Article article, Long replyId) {
        Reply reply = replyRepository.findReplyById(replyId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        article.checkIncludeReply(reply);
        return reply;
    }

    @Transactional(readOnly = true)
    public List<Reply> findRepliesByArticle(Article article) {
        return replyRepository.findRepliesByArticle(article);
    }
}
