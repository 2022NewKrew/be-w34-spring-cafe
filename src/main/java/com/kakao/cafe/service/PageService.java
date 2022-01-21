package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageNumberDto;
import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    public static final int PAGE_ARTICLE_NUMBER = 15;
    public static final int PAGE_NUMBER = 5;

    public boolean hasLeftButton(int pageIndex) {
        if(pageIndex == 0)
            return false;
        return true;
    }

    public boolean hasRightButton(List<Article> articles, int pageIndex) {
        int maxPageNumber = (articles.size() - 1) / PAGE_ARTICLE_NUMBER + 1;
        int maxPageIndex = (maxPageNumber - 1) / PAGE_NUMBER;
        if(pageIndex == maxPageIndex)
            return false;
        return true;
    }

    public List<Article> getSubArticles(List<Article> articles, int pageNumber) {
        int startIndex = (pageNumber - 1) * PAGE_ARTICLE_NUMBER;
        int endIndex = Math.min(articles.size(), pageNumber * PAGE_ARTICLE_NUMBER);
        return new ArrayList<>(articles.subList(startIndex, endIndex));
    }

    public List<PageNumberDto> getPageNumbers(List<Article> articles, int pageIndex) {
        List<PageNumberDto> pageNumbers = new ArrayList<>();
        int startPageNumber = pageIndex * PAGE_NUMBER + 1;
        int endPageNumber = Math.min((pageIndex + 1) * PAGE_NUMBER, (articles.size() - 1) / PAGE_ARTICLE_NUMBER + 1);
        for(int i = startPageNumber; i <= endPageNumber; i++)
            pageNumbers.add(new PageNumberDto(i));
        return pageNumbers;
    }
}
