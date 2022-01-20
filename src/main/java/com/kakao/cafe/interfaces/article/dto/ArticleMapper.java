package com.kakao.cafe.interfaces.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.article.dto.request.UpdateArticleRequestDto;
import com.kakao.cafe.interfaces.article.dto.request.WriteArticleRequestDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleListResponseDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {
    private ArticleMapper() {
    }

    public static List<ArticleListResponseDto> convertEntityListToResponseDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(e -> new ArticleListResponseDto(e.getId(), e.getWriter().getName(), e.getCreatedAt(), e.getTitle()))
                .collect(Collectors.toList());
    }

    public static ArticleVo convertWriteArticleDtoToVo(WriteArticleRequestDto writeArticleRequestDto, User user) {
        return new ArticleVo(user,
                writeArticleRequestDto.getTitle(),
                writeArticleRequestDto.getContents());
    }

    public static ArticleVo convertUpdateArticleDtoToVo(UpdateArticleRequestDto updateArticleRequestDto, User user) {
        return new ArticleVo(updateArticleRequestDto.getId(),
                user,
                updateArticleRequestDto.getTitle(),
                updateArticleRequestDto.getContents());
    }

    public static ArticleResponseDto convertEntityToResponseDto(Article article) {
        return new ArticleResponseDto(article.getId(),
                article.getWriter().getName(),
                article.getCreatedAt(),
                article.getTitle(),
                article.getContents());
    }
}
