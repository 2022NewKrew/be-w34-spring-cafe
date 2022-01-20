package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.WriteArticleRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public class WriteArticleService implements WriteArticleUseCase {

    private final RegisterArticlePort registerArticlePort;

    public WriteArticleService(RegisterArticlePort registerArticlePort) {
        this.registerArticlePort = registerArticlePort;
    }

    @Override
    public void writeArticle(WriteArticleRequest writeArticleRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        registerArticlePort.registerArticle(writeArticleRequest);
    }
}
