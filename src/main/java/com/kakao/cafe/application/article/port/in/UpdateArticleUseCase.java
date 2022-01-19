package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.UpdateRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public interface UpdateArticleUseCase {

    void updateArticle(UpdateRequest updateRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException;
}
