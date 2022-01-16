package com.kakao.cafe.service.article;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public void uploadArticle(ArticleDto articleDto) {
        articleRepository.save(articleMapper.toArticleEntity(articleDto));
    }

    @Override
    public List<ArticleDto> allArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();

        return articleEntities.stream()
                .map(articleMapper::toArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto retrieveArticle(Long articleId) {
        ArticleEntity articleEntity = articleRepository.findOne(articleId);

        return articleMapper.toArticleDto(articleEntity);
    }
}
