package com.kakao.cafe.service;


import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.ReplyRestResponseDTO;
import com.kakao.cafe.dto.UserDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface ArticleService {

    long insertArticle(ArticleDTO article);

    void insertReply(ReplyDTO reply, long userId);

    void validArticleOwnerShip(ArticleDTO article, UserDTO user);

    List<ArticleDTO> getArticleList();

    ArticleDTO getArticleById(long articleId);

    void getArticle(long articleId, UserDTO user, Model model);

    void updateArticle(UserDTO user, long id, ArticleDTO article);

    void deleteArticle(long id, ArticleDTO article, UserDTO user);

    void deleteReply(long userId, long replyId);

    // lastReplyId : 사용자에게 로딩된 마지막 댓글 다음의 내용을 리턴하기 위해 필요한 값
    ReplyRestResponseDTO getArticleReplies(long articleId, long lastReplyId);
}
