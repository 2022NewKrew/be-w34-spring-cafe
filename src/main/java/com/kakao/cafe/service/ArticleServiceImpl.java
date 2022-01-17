package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;

    @Override
    public void register(ArticleDto dto) {
        Article entity = dtoToEntity(dto);
        articleRepository.save(entity);
    }

    @Override
    public ArticleDto read(Long articleId) {
        Optional<Article> result = articleRepository.findById(articleId);
        if (result.isEmpty())
            throw new ArticleNotFoundException();
        return entityToDto(result.get());
    }

    @Override
    public PageResultDto<ArticleDto, Article> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<Article> result = articleRepository.findAll(pageable);
        Function<Article, ArticleDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
