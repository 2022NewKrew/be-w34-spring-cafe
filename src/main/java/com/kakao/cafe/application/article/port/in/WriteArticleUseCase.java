package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public interface WriteArticleUseCase {

    void writeArticle(WriteRequest writeRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException;
}
