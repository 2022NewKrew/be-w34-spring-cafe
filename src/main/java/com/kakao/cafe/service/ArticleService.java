package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticlePageDto;
import com.kakao.cafe.dto.article.ArticlePageInfoDto;
import com.kakao.cafe.vo.ArticleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private static final int ARTICLES_PER_PAGE = 15;
    private static final int COUNT_OF_PAGE = 5;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;
    private boolean isFirst = true;
    private int countOfArticle = 0;

    public void addArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = modelMapper.map(articleDto, ArticleVo.class);
        articleDao.save(articleVo);
        countOfArticle++;
    }

    public ArticleDto getArticle(int articleId) {
        ArticleVo articleVo = articleDao.findByArticleId(articleId);
        return modelMapper.map(articleVo, ArticleDto.class);
    }

    public List<ArticleDto> getArticles(int page) {
        if (isFirst) {
            countOfArticle = getCountOfArticle();
            isFirst = false;
        }
        int endPage = ((countOfArticle - 1) / ARTICLES_PER_PAGE) + 1;
        int offset = (page - 1) * ARTICLES_PER_PAGE;
        log.info("마지막 페이지 : {}", endPage);
        return articleDao.findAllByPage(offset, ARTICLES_PER_PAGE).stream()
                .map(articleVo -> modelMapper.map(articleVo, ArticleDto.class))
                .collect(Collectors.toList());
    }

    public void updateArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = modelMapper.map(articleDto, ArticleVo.class);
        articleDao.update(articleVo);
    }

    public void deleteArticle(int articleId) {
        articleDao.deleteByArticleId(articleId);
        countOfArticle--;
    }

    public ArticlePageInfoDto getCurPageInfo(int page) {
        int startPageOffset = ((page - 1) / COUNT_OF_PAGE) * COUNT_OF_PAGE;
        int endPage = ((countOfArticle - 1) / ARTICLES_PER_PAGE) + 1;
        int curStartPage = startPageOffset + 1;
        int curEndPage = Math.min(startPageOffset + COUNT_OF_PAGE, endPage);
        List<ArticlePageDto>articlePageDtoList = new ArrayList<>();
        for (int myPage = curStartPage; myPage <= curEndPage; myPage++) {
            articlePageDtoList.add(new ArticlePageDto(myPage));
        }
        return new ArticlePageInfoDto(curStartPage,curEndPage,articlePageDtoList);
    }

    public boolean hasNextPage(int curEndPage) {
        int endPage = ((countOfArticle - 1) / ARTICLES_PER_PAGE) + 1;
        return curEndPage < endPage;
    }

    public int getCountOfArticle() {
        return articleDao.count();
    }
}
