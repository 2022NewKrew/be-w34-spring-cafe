package com.kakao.cafe.article.service;

import java.util.List;

import com.kakao.cafe.article.dto.request.ReplyCreateRequestDTO;
import com.kakao.cafe.article.dto.response.ReplyFindResponseDTO;

public interface ReplyService {
    void create(ReplyCreateRequestDTO replyCreateRequestDTO);

    ReplyFindResponseDTO getReplyById(int id);

    List<ReplyFindResponseDTO> getAllReplyByArticleId(int articleId);

    void remove(int id);
}
