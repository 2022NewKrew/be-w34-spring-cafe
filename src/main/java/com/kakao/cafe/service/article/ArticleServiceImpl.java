package com.kakao.cafe.service.article;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public void uploadArticle(ArticleDto articleDto) {
        articleRepository.save(articleMapper.toArticleEntity(articleDto));
    }

    @Override
    public List<ArticleDto> allArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();

        return articleMapper.toArticleDtoList(articleEntities);
    }

    @Override
    public ArticleDto retrieveArticle(Long articleId) {
        ArticleEntity articleEntity = articleRepository.findOne(articleId);
        articleRepository.updateViewsById(articleId, articleEntity.getViews() + 1);

        return articleMapper.toArticleDto(articleEntity);
    }

    @Override
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public void updateArticle(ArticleDto articleDto) {
        articleRepository.updateTitleAndContentById(articleDto.getId(), articleDto.getTitle(), articleDto.getContent());
    }
}
