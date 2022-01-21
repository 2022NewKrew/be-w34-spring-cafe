package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public interface UpdateArticlePort {

    void updateArticle(UpdateArticleRequest updateArticleRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException;
}
