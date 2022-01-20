package com.kakao.cafe.article.factory;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleFactory {

    public static Article toArticle(Long userFK, String name, QuestionDTO questionDTO) {
        return new Article(userFK, name, questionDTO.getTitle(), questionDTO.getContents());
    }

    public static Article toArticle(Long userFK, String name, Long articlePK, Long countOfComment, QuestionDTO questionDTO) {
        return new Article(articlePK, userFK, name, questionDTO.getTitle(), questionDTO.getContents(), countOfComment);
    }

    public static QuestionDTO toDTO(String title, String contents) {
        return new QuestionDTO(title, contents);
    }

}
