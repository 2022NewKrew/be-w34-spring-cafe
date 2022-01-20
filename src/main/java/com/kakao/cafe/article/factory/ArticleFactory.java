package com.kakao.cafe.article.factory;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleFactory {

    public static Article toArticle(Long userFK, String name, QuestionDTO questionDTO) {
        return new Article(userFK, name, questionDTO.getTitle(), questionDTO.getContents());
    }
}
