package com.kakao.cafe.article.service;

import java.util.List;

import com.kakao.cafe.article.dto.request.ArticleReplyCreateRequestDTO;
import com.kakao.cafe.article.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.article.dto.response.ArticleReplyFindResponseDTO;

public interface ArticleReplyService {
    void create(ArticleReplyCreateRequestDTO articleReplyCreateRequestDTO);

    List<ArticleReplyFindResponseDTO> getAllArticleReplyByArticleId(int articleId);

    void remove(int id);
}
