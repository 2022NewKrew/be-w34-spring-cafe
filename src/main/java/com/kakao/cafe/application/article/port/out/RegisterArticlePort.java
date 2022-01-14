package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public interface RegisterArticlePort {

    void registerArticle(WriteRequest writeRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException;
}
