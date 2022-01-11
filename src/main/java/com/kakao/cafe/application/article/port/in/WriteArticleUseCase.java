package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.WriteRequest;

public interface WriteArticleUseCase {

    void writeArticle(WriteRequest writeRequest);
}
