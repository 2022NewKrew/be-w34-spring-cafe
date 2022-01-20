package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyInfoResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReplyRepository {
    Long save(Reply reply);

    List<ReplyInfoResponse> findAll(Long articleId);
}
