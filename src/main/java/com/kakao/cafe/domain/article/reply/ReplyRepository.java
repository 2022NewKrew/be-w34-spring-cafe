package com.kakao.cafe.domain.article.reply;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Long save(Reply reply);
    List<Reply> findRepliesByArticle(Article article);
    Optional<Reply> findReplyById(Long replyId);
    Long delete(Reply reply);
}
