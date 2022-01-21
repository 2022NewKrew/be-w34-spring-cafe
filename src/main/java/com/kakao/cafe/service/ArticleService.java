package com.kakao.cafe.service;


import com.kakao.cafe.dto.*;
import org.springframework.ui.Model;

public interface ArticleService {

    void insertArticle(ArticleDTO article);

    RestResponseDTO insertReplyAndGetReplies(ReplyDTO reply, Long userId, long lastReplyId);

    void getArticleForm(long articleId, UserDTO user, Model model);

    void getArticleList(Long page, Model model);

    ArticleDTO getArticleById(long articleId);

    void getArticle(long articleId, UserDTO user, Model model);

    void updateArticle(UserDTO user, long id, ArticleDTO article);

    void deleteArticle(long id, UserDTO user);

    RestResponseDTO deleteReply(long userId, long articleId, long replyId);

    // lastReplyId : 사용자에게 로딩된 마지막 댓글 다음의 내용을 리턴하기 위해 필요한 값
    ReplyRestResponseDTO getArticleReplies(long articleId, long lastReplyId);
}
