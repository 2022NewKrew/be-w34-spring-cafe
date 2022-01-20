package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;

public interface ArticleService {
    void register(ArticleDto dto);

    ArticleDto read(Long articleId);

    void modify(ArticleDto dto);

    PageResultDto<ArticleDto, Article> getList(PageRequestDto requestDto);

    default Article dtoToEntity(ArticleDto dto) {
        return Article.builder()
                .articleId(dto.getArticleId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(User.builder().email(dto.getWriterEmail()).username(dto.getWriterUsername()).build())
                .viewCount(dto.getViewCount())
                .build();
    }

    default ArticleDto entityToDto(Article entity) {
        return ArticleDto.builder()
                .articleId(entity.getArticleId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerEmail(entity.getWriter().getEmail())
                .writerUsername(entity.getWriter().getUsername())
                .viewCount(entity.getViewCount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
