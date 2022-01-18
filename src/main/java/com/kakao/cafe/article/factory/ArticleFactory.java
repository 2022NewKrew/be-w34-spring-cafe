package com.kakao.cafe.article.factory;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleFactory {

    public static Article toArticle(Long id, QuestionDTO questionDTO) {
        return new Article(id, questionDTO.getWriter(), questionDTO.getTitle(), questionDTO.getContents());
    }

    public static Article toArticle(QuestionDTO questionDTO) {
        return new Article(questionDTO.getWriter(), questionDTO.getTitle(), questionDTO.getContents());
    }
}
