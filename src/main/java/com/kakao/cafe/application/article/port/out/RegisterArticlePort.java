package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.WriteRequest;

public interface RegisterArticlePort {

    void registerArticle(WriteRequest writeRequest);
}
