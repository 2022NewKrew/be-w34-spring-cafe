package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class ArticleNotFoundException extends BusinessException {

    public ArticleNotFoundException(){
        super(ErrorType.ARTICLE_NOT_FOUND);
    }
}
