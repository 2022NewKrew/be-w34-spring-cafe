package com.kakao.cafe.article.factory;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleFactory {

    public Article of(Long id, QuestionDTO questionDTO) {
        return new Article(id, questionDTO);
    }

    public Article of(QuestionDTO questionDTO) {
        return new Article(questionDTO);
    }
}
