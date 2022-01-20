package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.dto.response.ArticleResDto;
import com.kakao.cafe.article.entity.ArticleEntity;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.exception.UpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public void saveArticle(ArticleReqDto articleDto) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .writer(articleDto.getWriter())
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .build();

        articleRepository.save(articleEntity);
    }

    @Transactional(readOnly = true)
    public List<ArticleResDto> getAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();
        return articleMapper.toArticleResDtoList(articleEntities);
    }

    @Transactional(readOnly = true)
    public ArticleDetailResDto getArticle(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow();

        return articleMapper.toArticleDetailResDto(articleEntity);
    }

    public void update(ArticleReqDto articleReqDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleReqDto.getId())
                .orElseThrow();

        articleEntity.update(articleReqDto.getTitle(), articleReqDto.getContents());
        articleRepository.save(articleEntity);
    }

    public void delete(Long id, String username) {
        checkUpdatable(id, username);
        articleRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public ArticleDetailResDto getArticleForUpdate(Long id, String username) {
        checkUpdatable(id, username);
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow();

        return articleMapper.toArticleDetailResDto(article);
    }

    private void checkUpdatable(Long id, String username) {
        List<ArticleEntity> articles = articleRepository.findByIdAndWriter(id, username);
        if (articles.isEmpty()) {
            throw new UpdateException("글 작성자만 수정/삭제가 가능합니다.");
        }
    }
}
