package com.kakao.cafe.domain.article;

import com.kakao.cafe.web.dto.article.ArticleAddRequestDto;
import com.kakao.cafe.web.dto.article.ArticlesListResponseDto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Articles {
    private final List<Article> articles = new ArrayList<>();
    private int maxIndex;

    public void add(ArticleAddRequestDto artDto) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedToday = today.format(dateFormatter);

        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        String formattedNow = now.format(timeFormatter);

        articles.add(new Article(maxIndex++, formattedToday, formattedNow, artDto));
    }

    public ArticlesListResponseDto findAll() {
        return new ArticlesListResponseDto(articles);
    }
}
